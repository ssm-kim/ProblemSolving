import java.io.*;
import java.util.*;

public class Main {

    static final int CLEAN_AIR = 0;
    static int r, c, t;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static ArrayList<int[]> airCleanerPos = new ArrayList<>();  // [0]: 위쪽, [1]: 아래쪽 공기청정기

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());  // 행
        c = Integer.parseInt(st.nextToken());  // 열
        t = Integer.parseInt(st.nextToken());  // t초 동안 진행
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    airCleanerPos.add(new int[] {i, j});
                }  // 공기 청정기 좌표 저장
            }
        }

        // t초 동안 반복: 확산 → 공기청정기 작동
        for (int i = 0; i < t; i++) {
            ArrayList<int[]> dustPos = findDustPositions();
            map = spreadDust(dustPos);       // 동시 확산
            moveUp(airCleanerPos.get(0));    // 반시계 회전
            moveDown(airCleanerPos.get(1));  // 시계 회전
        }

        // 최종 미세먼지 총량 계산
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1 || map[i][j] == 0) continue;
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    // 위쪽 공기청정기: 반시계 방향 회전
    static void moveUp(int[] pos) {
        Queue<Integer> rotationQueue = new LinkedList<>();
        rotationQueue.offer(CLEAN_AIR);  // 공기청정기에서 나오는 깨끗한 공기 0 고정
        int startX = pos[0];

        // 회전 경로를 1차원으로 펼쳐서 큐에 저장: 우 → 상 → 좌 → 하
        // 아래쪽, 오른쪽 방향
        for (int col = 1; col < c; col++) {
            rotationQueue.offer(map[startX][col]);
        }
        // 오른쪽, 위쪽 방향
        for (int row = startX - 1; row >= 0; row--) {
            rotationQueue.offer(map[row][c - 1]);
        }
        // 위쪽, 왼쪽 방향으로
        for (int col = c - 2; col >= 0; col--) {
            rotationQueue.offer(map[0][col]);
        }
        // 왼쪽, 아래쪽 방향으로
        for (int row = 1; row < startX; row++) {
            rotationQueue.offer(map[row][0]);
        }

        // 큐에서 하나씩 꺼내며 반시계 방향으로 한 칸씩 이동
        for (int col = 1; col < c; col++) {
            map[startX][col] = rotationQueue.poll();
        }
        for (int row = startX - 1; row >= 0; row--) {
            map[row][c - 1] = rotationQueue.poll();
        }
        for (int col = c - 2; col >= 0; col--) {
            map[0][col] = rotationQueue.poll();
        }
        for (int row = 1; row < startX; row++) {
            map[row][0] = rotationQueue.poll();
        }
    }

    // 아래쪽 공기청정기: 시계 방향 회전
    static void moveDown(int[] pos) {
        Queue<Integer> rotationQueue = new LinkedList<>();
        rotationQueue.offer(CLEAN_AIR);  // 미세 먼지가 없는 바람이므로 0 고정
        int startX = pos[0];

        // 회전 경로를 1차원으로 펼쳐서 큐에 저장: 우 → 하 → 좌 → 상
        // 위쪽, 오른쪽 방향
        for (int col = 1; col < c; col++) {
            rotationQueue.offer(map[startX][col]);
        }
        // 오른쪽, 아래쪽 방향
        for (int row = startX + 1; row < r; row++) {
            rotationQueue.offer(map[row][c - 1]);
        }
        // 아래쪽, 왼쪽 방향
        for (int col = c - 2; col >= 0; col--) {
            rotationQueue.offer(map[r - 1][col]);
        }
        // 왼쪽, 위쪽 방향
        for (int row = r - 2; row > startX; row--) {
            rotationQueue.offer(map[row][0]);
        }

        // 큐에서 하나씩 꺼내며 시계 방향으로 한 칸씩 이동
        for (int col = 1; col < c; col++) {
            map[startX][col] = rotationQueue.poll();
        }
        for (int row = startX + 1; row < r; row++) {
            map[row][c - 1] = rotationQueue.poll();
        }
        for (int col = c - 2; col >= 0; col--) {
            map[r - 1][col] = rotationQueue.poll();
        }
        for (int row = r - 2; row > startX; row--) {
            map[row][0] = rotationQueue.poll();
        }
    }

    // 현재 미세먼지가 있는 모든 위치 반환
    static ArrayList<int[]> findDustPositions() {
        ArrayList<int[]> dustPositions = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    dustPositions.add(new int[] {i, j});
                }
            }
        }
        return dustPositions;
    }

    // 모든 미세먼지 동시 확산 처리
    static int[][] spreadDust(ArrayList<int[]> startPos) {
        int[][] newMap = new int[r][c];

        // 공기 청정기 위치 설정
        newMap[airCleanerPos.get(0)[0]][airCleanerPos.get(0)[1]] = -1;
        newMap[airCleanerPos.get(1)[0]][airCleanerPos.get(1)[1]] = -1;

        for (int[] current : startPos) {
            int cx = current[0];
            int cy = current[1];
            int spreadValue = (int) Math.floor(map[cx][cy] / 5);  // 확산량 (자동 버림)
            int dirCnt = 0;  // 확산된 방향의 갯수

            // 확산량이 0이면 원래 값만 유지
            if (spreadValue == 0) {
                newMap[cx][cy] += map[cx][cy];
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 + 공기청정기 체크
                if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == -1) continue;

                dirCnt++;
                newMap[nx][ny] += spreadValue;
            }

            // 현재 위치의 남은 미세먼지 += 원래값 - (확산량 × 확산방향수)
            newMap[cx][cy] += map[cx][cy] - (spreadValue * dirCnt);
        }
        return newMap;
    }
}