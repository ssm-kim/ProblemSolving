import java.io.*;
import java.util.*;

class Node {
    int x, y, meltCnt;

    public Node(int x, int y, int meltCnt) {
        this.x = x;
        this.y = y;
        this.meltCnt = meltCnt;
    }
}

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Node> iceMountain;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        iceMountain = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    iceMountain.add(new Node (i, j, 0));
                }
            }
        }

        int year = 0;
        boolean isSeparate = false;
        while (!iceMountain.isEmpty()) {

            // 녹일 빙산 갯수 구한 후 한번에 녹이기
            melting();

            // 빙산들이 서로 갈라졌는지 확인
            boolean[][] visited = new boolean[n][m];
            int cnt = 0;
            for (Node cur : iceMountain) {
                if (!visited[cur.x][cur.y]) {
                    dfs(cur.x, cur.y, visited);
                    cnt++;
                }
            }

            year++;
            if (cnt >= 2) {
                System.out.println(year);
                return;
            }
        }
        System.out.println(0);
    }

    static void dfs(int cx, int cy, boolean[][] visited) {
        visited[cx][cy] = true;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            if (board[nx][ny] > 0) {
                dfs(nx, ny, visited);
            }
        }
    }

    static void melting() {
        // 1단계: 각 빙산의 인접 바다 수 계산 (동시에 녹여야 하므로 먼저 전부 계산)
        for (Node cur : iceMountain) {
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == 0) cur.meltCnt++;
            }
        }

        // 2단계: 한번에 녹이고, 살아있는 빙산만 다음 리스트에 추가
        ArrayList<Node> nextTarget = new ArrayList<>();
        for (Node cur : iceMountain) {
            board[cur.x][cur.y] = Math.max(board[cur.x][cur.y] - cur.meltCnt, 0);
            if (board[cur.x][cur.y] > 0) {
                cur.meltCnt = 0;  // 다음 턴을 위해 초기화
                nextTarget.add(cur);
            }
        }
        iceMountain = nextTarget;
    }
}