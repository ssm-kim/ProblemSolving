import java.io.*;
import java.util.*;

public class Main {

    static int w, h;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visited = new boolean[h][w];

            int cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        cnt++;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    static void bfs(int sx, int sy) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }

                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }
}