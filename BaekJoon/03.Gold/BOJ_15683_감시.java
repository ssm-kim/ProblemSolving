import java.io.*;
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

    static int n, m, answer;
    static int[][] board;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    // CCTV 번호별 가능한 방향 조합
    static int[][][] dirs = new int[][][] {
        {{}},
        {{0}, {1}, {2}, {3}},
        {{1, 3}, {0, 2}},
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
        {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},
        {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // CCTV 위치 저장
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int depth) {
        // 모든 CCTV의 방향을 정했으면 사각지대 계산
        if (depth == cctvList.size()) {
            int invisibleCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 0) invisibleCnt++;
                }
            }
            answer = Math.min(answer, invisibleCnt);
            return;
        }

        // 현재 CCTV의 모든 가능한 방향 시도
        CCTV current = cctvList.get(depth);
        for (int[] dir : dirs[current.number]) {
            ArrayList<int[]> observerPath = observer(dir, current);
            dfs(depth + 1);
            recovery(observerPath);  // 백트래킹
        }
    }

    // 감시 영역 복구
    static void recovery(ArrayList<int[]> observerPath) {
        for (int[] path : observerPath) {
            board[path[0]][path[1]] = 0;
        }
    }

    // 주어진 방향으로 감시 영역 표시
    static ArrayList<int[]> observer(int[] dir, CCTV current) {
        ArrayList<int[]> path = new ArrayList<>();

        for (int coordinateIdx : dir) {
            int cx = current.x;
            int cy = current.y;

            // 각 방향으로 벽을 만날 때까지 진행
            while (true) {
                cx += dx[coordinateIdx];
                cy += dy[coordinateIdx];

                if (cx < 0 || cx >= n || cy < 0 || cy >= m || board[cx][cy] == 6) break;
                if (board[cx][cy] >= 1 && board[cx][cy] <= 5) continue;  // CCTV는 통과

                board[cx][cy] = current.number;
                path.add(new int[] {cx, cy});
            }
        }
        return path;
    }
}