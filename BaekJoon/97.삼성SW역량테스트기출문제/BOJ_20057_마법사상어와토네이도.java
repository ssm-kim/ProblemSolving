import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int n, totalSand;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // remain : 알파로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다.
        // 이미 모래가 있다면 더 해준다.
        LinkedList<int[]> queue = rotate();
        totalSand = 0;
        while (queue.size() > 1) {
            int[] current = queue.pollLast();
            int[] next = queue.peekLast();

            usedSkill(current, next);
        }
        System.out.println(totalSand);
    }

    static void usedSkill(int[] current, int[] next) {
        // 동 남 서 북
        int dir = current[2];

        // 각 방향을 반대로
        switch (dir) {
            case 0: dir = 2; break;
            case 1: dir = 3; break;
            case 2: dir = 0; break;
            case 3: dir = 1; break;
        }
        int cx = next[0];
        int cy = next[1];

        // 각 방향별 토네이도 패턴 정의
        int[] dx, dy, alphaPos;
        double[] ratio;
        switch (dir) {
            case 0:  // 동쪽 이동
                dx = new int[] {2, -1, -1, -1, 0, 1, 1, 1, -2};
                dy = new int[] {0, -1, 0, 1, 2, -1, 0, 1, 0};
                ratio = new double[] {0.02, 0.01, 0.07, 0.10, 0.05, 0.01, 0.07, 0.10, 0.02};
                alphaPos = new int[] {cx, cy + 1};
                break;
            case 1:  // 남쪽 이동
                dx = new int[]{0, -1, 0, 1, 2, -1, 0, 1, 0};
                dy = new int[]{-2, -1, -1, -1, 0, 1, 1, 1, 2};
                ratio = new double[]{0.02, 0.01, 0.07, 0.10, 0.05, 0.01, 0.07, 0.10, 0.02};
                alphaPos = new int[] {cx + 1, cy};
                break;
            case 2:  // 서쪽 이동
                dx = new int[] {2, -1, -1, -1, 0, 1, 1, 1, -2};
                dy = new int[] {0, -1, 0, 1, -2, -1, 0, 1, 0};
                ratio = new double[] {0.02, 0.10, 0.07, 0.01, 0.05, 0.10, 0.07, 0.01, 0.02};
                alphaPos = new int[] {cx, cy - 1};
                break;
            case 3:  // 북쪽 이동
                dx = new int[]{0, -1, 0, 1, -2, -1, 0, 1, 0};
                dy = new int[]{-2, -1, -1, -1, 0, 1, 1, 1, 2};
                ratio = new double[]{0.02, 0.10, 0.07, 0.01, 0.05, 0.10, 0.07, 0.01, 0.02};
                alphaPos = new int[] {cx - 1, cy};
                break;
            default:
                return;
        }

        // 토네이도 실행: 9개 위치에 비율에 따라 모래 분산
        spreadSand(cx, cy, dx, dy, ratio, alphaPos);
    }

    // 토네이도 모래 분산 로직
    static void spreadSand(int cx, int cy, int[] dx, int[] dy, double[] ratio, int[] alphaPos) {
        int currentSand = 0;

        // 9개 위치에 비율에 따라 모래 분산
        for (int i = 0; i < 9; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            int sandAmount = (int) (map[cx][cy] * ratio[i]);
            currentSand += sandAmount;

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                totalSand += sandAmount;  // 격자 밖으로 나간 모래
            } else {
                map[nx][ny] += sandAmount;  // 격자 안에 모래 추가
            }
        }

        // 알파 위치에 남은 모래 배치
        int alphaSand = map[cx][cy] - currentSand;
        if (alphaPos[0] >= 0 && alphaPos[0] < n && alphaPos[1] >= 0 && alphaPos[1] < n) {
            map[alphaPos[0]][alphaPos[1]] += alphaSand;
        } else {
            totalSand += alphaSand;  // 알파도 격자 밖이면 누적
        }

        map[cx][cy] = 0;  // 토네이도가 지나간 자리는 모래 제거
    }

    // 바깥 → 안 나선형 경로 생성
    static LinkedList<int[]> rotate() {
        int[] dx = {0, 1, 0, -1};  // 동 남 서 북
        int[] dy = {1, 0, -1, 0};

        boolean[][] visited = new boolean[n][n];
        LinkedList<int[]> coordinate = new LinkedList<>();
        int dir = 0;  // 동쪽부터 시작
        int cx = 0, cy = 0;

        while (true) {
            if (coordinate.size() == n * n) {
                break;
            }

            // 현재 위치 추가
            coordinate.offer(new int[]{cx, cy, dir});
            visited[cx][cy] = true;

            // 각 방향으로 가능한 만큼 이동
            for (int i = 0; i < n; i++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir] + i;

                if (ny >= n || visited[nx][ny]) {
                    cy = ny - 1;
                    break;
                }
                visited[nx][ny] = true;
                coordinate.offer(new int[]{nx, ny, dir});
            }
            dir = (dir + 1) % 4;

            for (int i = 0; i < n; i++) {
                int nx = cx + dx[dir] + i;
                int ny = cy + dy[dir];

                if (nx >= n || visited[nx][ny]) {
                    cx = nx - 1;
                    break;
                }
                visited[nx][ny] = true;
                coordinate.offer(new int[]{nx, ny, dir});
            }
            dir = (dir + 1) % 4;

            for (int i = 0; i < n; i++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir] - i;

                if (ny < 0 || visited[nx][ny]) {
                    cy = ny + 1;
                    break;
                }
                visited[nx][ny] = true;
                coordinate.offer(new int[]{nx, ny, dir});
            }
            dir = (dir + 1) % 4;

            for (int i = 0; i < n; i++) {
                int nx = cx + dx[dir] - i;
                int ny = cy + dy[dir];

                if (nx < 0 || visited[nx][ny]) {
                    cx = nx + 1;
                    break;
                }
                visited[nx][ny] = true;
                coordinate.offer(new int[]{nx, ny, dir});
            }
            dir = (dir + 1) % 4;
            cy++;  // 다음 레이어로 이동
        }
        return coordinate;
    }
}