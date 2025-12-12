import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        // 초기 빙산 좌표들 저장
        ArrayList<int[]> target = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    target.add(new int[] {i, j});
                }
            }
        }

        int year = 0;
        while (true) {
            // 1. 녹을 양 계산 (동시에 녹이기 위해 미리 계산)
            ArrayList<int[]> path = meltingCheck(target);

            // 2. 한번에 빙산 녹이기
            melting(path);

            year++; // 1년 경과

            // 3. 연결요소 개수 확인 (빙산이 몇 덩어리로 분리되었는지)
            visited = new boolean[n][m];
            int islandCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] != 0 && !visited[i][j]) {
                        dfs(i, j);
                        islandCnt++;
                    }
                }
            }

            // 4. 종료 조건 체크
            if (islandCnt == 0) {        // 모든 빙산 녹음
                year = 0;
                break;
            } else if (islandCnt >= 2) { // 2덩어리 이상 분리
                break;
            }
        }
        System.out.println(year);
    }

    // 연결된 빙산들 탐색
    static void dfs(int cx, int cy) {
        visited[cx][cy] = true;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            if (board[nx][ny] != 0) { // 빙산이면 계속 탐색
                dfs(nx, ny);
            }
        }
    }

    // 실제 빙산 녹이기
    static void melting(ArrayList<int[]> path) {
        for (int[] pos : path) {
            int cx = pos[0];
            int cy = pos[1];
            int meltCnt = pos[2];

            // 0 이하로는 안 내려감
            board[cx][cy] = Math.max(board[cx][cy] - meltCnt, 0);
        }
    }

    // 각 빙산이 얼마나 녹을지 계산
    static ArrayList<int[]> meltingCheck(ArrayList<int[]> target) {
        ArrayList<int[]> info = new ArrayList<>();

        for (int[] pos : target) {
            int cx = pos[0];
            int cy = pos[1];

            // 4방향 바다 개수만큼 녹음
            int heightCnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] != 0) continue;

                heightCnt++; // 바다 발견시 녹을 양 증가
            }
            info.add(new int[] {cx, cy, heightCnt});
        }
        return info;
    }
}