import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    int moveCnt;

    public Point(int x, int y, int moveCnt) {
        this.x = x;
        this.y = y;
        this.moveCnt = moveCnt;
    }
}

public class Main {

    static int l;
    static boolean[][] visited;
    static int[][] board;
    static int[] dx = {-1, -2, 1, 2, -2, -1, 2, 1};
    static int[] dy = {-2, -1, 2, 1, 1, 2, -1 ,-2};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            l = Integer.parseInt(br.readLine());
            board = new int[l][l];

            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            visited = new boolean[l][l];
            int answer = bfs(sx, sy, ex, ey);
            System.out.println(answer);
        }
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int cx = p.x;
            int cy = p.y;

            if (cx == ex && cy == ey) {
                return p.moveCnt;
            }  // 목적지 도착

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= l || ny < 0 || ny >= l || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny, p.moveCnt + 1));
            }
        }
        return -1;
    }
}