import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] originBoard, updateBoard;
    static boolean[][] visited;
    static ArrayList<int[]> zeroStart = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        originBoard = new int[n][m];
        updateBoard = new int[n][m];
        int sx = -1, sy = -1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                originBoard[i][j] = Integer.parseInt(st.nextToken());
                if (originBoard[i][j] == 2) {
                    sx = i; sy = j;
                }
                if (originBoard[i][j] == 0) {
                    zeroStart.add(new int[] {i, j});
                }
            }
        }

        visited = new boolean[n][m];;
        bfs(sx, sy);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (updateBoard[i][j] == 0 && originBoard[i][j] == 1) {
                    updateBoard[i][j] = -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(updateBoard[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        updateBoard[sx][sy] = 0;
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m ||
                    originBoard[nx][ny] == 0 || visited[nx][ny]) continue;

                if (originBoard[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    updateBoard[nx][ny] = updateBoard[cx][cy] + 1;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }
}