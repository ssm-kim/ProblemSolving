import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Position implements Comparable<Position> {
    int row, col, emptyCount;

    public Position(int row, int col, int emptyCount) {
        this.row = row;
        this.col = col;
        this.emptyCount = emptyCount;
    }

    @Override
    public int compareTo(Position other) {
        return Integer.compare(other.emptyCount, emptyCount); // 빈 칸 수 내림차순
    }
}


public class Main {
    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static LinkedHashMap<Integer, HashSet<Integer>> student = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n * n; i++) {  // 학생 선호도 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentId = Integer.parseInt(st.nextToken());
            student.put(studentId, new HashSet<>());
            for (int j = 0; j < 4; j++) {
                student.get(studentId).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (Entry<Integer, HashSet<Integer>> entry : student.entrySet()) {  // 모든 학생을 순서대로 배치
            int studentId = entry.getKey();
            HashSet<Integer> likedStudents = entry.getValue();

            Position bestPosition = searchAdjPlace(likedStudents);
            map[bestPosition.row][bestPosition.col] = studentId;
        }

        int totalScore = 0;  // 만족도 점수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                HashSet<Integer> currentStudentLikes = student.get(map[i][j]);
                int satisfyCnt = 0;

                for (int k = 0; k < 4; k++) {  // 4방향 인접 칸에서 좋아하는 학생 수 계산
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    if (currentStudentLikes.contains(map[nx][ny])) {
                        satisfyCnt++;
                    }
                }
                switch (satisfyCnt) {  // 만족한 학생 수에 따른 점수 계산
                    case 1: totalScore += 1; break;
                    case 2: totalScore += 10; break;
                    case 3: totalScore += 100; break;
                    case 4: totalScore += 1000; break;
                }
            }
        }
        System.out.println(totalScore);
    }

    /*
     * 주어진 학생의 선호도에 따라 최적의 자리를 찾는 함수
     * 1. 좋아하는 학생이 인접한 칸에 가장 많은 칸
     * 2. 1번이 같다면, 인접한 빈 칸이 가장 많은 칸
     * 3. 2번도 같다면, 행 번호가 작은 칸 → 열 번호가 작은 칸
     */
    static Position searchAdjPlace(HashSet<Integer> likedStudents) {
        ArrayList<Position> candidates = new ArrayList<>();
        int maxLikedCount = 0;  // 인접한 좋아하는 학생 수의 최댓값

        // 모든 빈 칸을 검사하여 1번 조건 처리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) continue;  // 이미 학생이 앉아있다면 패스

                int emptyCount = 0;
                int likedCount = 0;
                for (int k = 0; k < 4; k++) {  // 4방향 인접 칸 검사
                    int nx = i + dx[k];
                    int ny = j + dy[k];


                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;  // 범위 체크

                    if (map[nx][ny] == 0 ) {
                        emptyCount++;  // 빈 칸 카운트
                    }
                    else if (likedStudents.contains(map[nx][ny])) {
                        likedCount++; // 좋아하는 학생 카운트
                    }
                }

                // 1번 조건: 좋아하는 학생이 인접한 칸에 가장 많은 칸 찾기
                if (likedCount > maxLikedCount) {
                    maxLikedCount = likedCount;
                    candidates.clear();
                    candidates.add(new Position (i, j, emptyCount));
                } else if (likedCount == maxLikedCount) {
                    candidates.add(new Position (i, j, emptyCount));
                }
            }
        }

        if (candidates.size() == 1) {  // 1번 조건으로 후보가 1개만 남았다면 반환
            return candidates.get(0);
        }

        // 2번 조건: 인접한 빈 칸이 가장 많은 칸 찾기
        Collections.sort(candidates);  // 빈 칸 수로 내림차순 정렬

        int sameEmptyCount = 1;
        for (int i = 1; i < candidates.size(); i++) {
            if (candidates.get(0).emptyCount == candidates.get(i).emptyCount) {
                sameEmptyCount++;
            } else {
                break;
            }
        }

        if (sameEmptyCount == 1) {  // 2번 조건으로 후보가 1개만 남았다면 반환
            return candidates.get(0);
        }

        // 3번 조건: 행 번호가 가장 작은 칸, 같다면 열 번호가 가장 작은 칸
        int cx = candidates.get(0).row;
        int cy = candidates.get(0).col;

        for (int i = 1; i < sameEmptyCount; i++) {
            int nx = candidates.get(i).row;
            int ny = candidates.get(i).col;

            if (cx > nx) {  // 다음 행 번호가 작다면, 갱신
                cx = nx;
                cy = ny;
            }
            else if (cx == nx && cy > ny) {  // 만약, 행 번호가 같다면 열 번호가 작은 칸으로 갱신
                cx = nx;
                cy = ny;
            }
        }
        return new Position(cx, cy, 0);
    }
}