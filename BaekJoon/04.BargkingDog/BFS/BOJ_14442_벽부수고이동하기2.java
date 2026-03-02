import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x, y, distance, destroyIdx;

    public Point(int x, int y, int distance, int destroyIdx) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.destroyIdx = destroyIdx;
    }
}

public class Main {

    static int n, m, k, answer;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        visited = new boolean[n][m][k + 1];
        answer = -1;
        bfs(0, 0);
        System.out.println(answer);
    }

    static void bfs(int sx, int sy) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx, sy, 1, k));
        visited[sx][sy][k] = true;  // 벽 안 부순 상태로 시작

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 목적지 도착 시 최단 거리 반환
            if (p.x == n - 1 && p.y == m - 1) {
                answer = p.distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][p.destroyIdx]) continue;

                // 빈 칸 이동 - 현재 상태 유지
                if (board[nx][ny] == 0) {
                    visited[nx][ny][p.destroyIdx] = true;
                    queue.offer(new Point(nx, ny, p.distance + 1, p.destroyIdx));
                }
                // 벽 부수기 - 상태 변경 (0 → 1)
                else if (board[nx][ny] == 1 && p.destroyIdx > 0) {
                    visited[nx][ny][p.destroyIdx] = true;
                    queue.offer(new Point(nx, ny, p.distance + 1, p.destroyIdx - 1));
                }
            }
        }
    }
}