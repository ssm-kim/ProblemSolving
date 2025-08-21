import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
    int x, y, number;

    public CCTV(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}

public class Main {

    static int n, m;
    static int blindCnt;
    static int[][] map;

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static ArrayList<CCTV> cctvNumbers = new ArrayList<>();

    static int[][][] cctvDir = {
        {},
        {{0}, {1}, {2}, {3}},                          // 1번 방향
        {{0, 2}, {1, 3}},                              // 2번 방향
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},              // 3번 방향
        {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},  // 4번 방향
        {{0, 1, 2, 3}}                                 // 5번 방향
    };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvNumbers.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        blindCnt = Integer.MAX_VALUE;
        dfs(0);
        System.out.println(blindCnt);
    }

    static void dfs(int depth) {
        // 모든 CCTV의 방향을 정했으면 사각지대 계산
        if (depth == cctvNumbers.size()) {
            int curBlindCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) curBlindCnt++;
                }
            }
            blindCnt = Math.min(blindCnt, curBlindCnt);
            return;
        }

        CCTV cctv = cctvNumbers.get(depth);  // 현재 cctv 번호
        int sx = cctv.x;
        int sy = cctv.y;
        int[][] dirs = cctvDir[cctv.number];

        // 현재 CCTV의 모든 가능한 방향에 대해 백트래킹
        for (int[] dir : dirs) {
            ArrayList<int[]> path = spread(sx, sy, dir);  // 감시 시작 + 변경된 좌표 기록
            dfs(depth + 1);                               // 다음 CCTV 처리
            recover(path);                                // 감시 해제 (백트래킹)
        }
    }

    static ArrayList<int[]> spread(int sx, int sy, int[] dir) {
        ArrayList<int[]> path = new ArrayList<>();  // 실제로 변경된 좌표만 기록

        for (int i = 0; i < dir.length; i++) {
            int nx = sx, ny = sy;
            while (true) {
                nx += dx[dir[i]];
                ny += dy[dir[i]];

                // 범위 벗어나거나 벽 만나면 그 방향 감시 종료
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 6) break;

                // 빈 공간만 감시 영역으로 변경하고 좌표 기록
                if (map[nx][ny] == 0) {
                    path.add(new int[] {nx, ny});
                    map[nx][ny] = 9;  // 빈칸일 때 감시 영역을 9로 표시
                }
            }
        }
        return path;
    }

    static void recover(ArrayList<int[]> path) {
        // 이 CCTV가 감시한 영역만 복구 (겹침 문제 해결)
        for (int[] coordinate : path) {
            int cx = coordinate[0];
            int cy = coordinate[1];
            map[cx][cy] = 0;
        }
    }
}