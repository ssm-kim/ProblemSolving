import java.io.*;
import java.util.*;

class Point {
    int x, y, skill, distance;

    public Point (int x, int y, int skill, int distance) {
        this.x = x;
        this.y = y;
        this.skill = skill;
        this.distance = distance;
    }
}
public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        visited = new boolean[2][n][m];
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == n - 1 && p.y == m - 1) return p.distance;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[p.skill][nx][ny]) continue;

                if (p.skill == 0) {  // 아직 벽 안 부순 상태
                    if (board[nx][ny] == 1) {  // 벽 → 부수고 스킬=1로 전환
                        visited[1][nx][ny] = true;
                        queue.offer(new Point(nx, ny, 1, p.distance + 1));
                    } else {  // 빈 칸 → 그대로 이동
                        visited[0][nx][ny] = true;
                        queue.offer(new Point(nx, ny, 0, p.distance + 1));
                    }
                }
                else {  // 이미 벽 부순 상태 → 빈 칸만 이동 가능
                    if (board[nx][ny] == 0) {
                        visited[1][nx][ny] = true;
                        queue.offer(new Point(nx, ny, 1, p.distance + 1));
                    }
                }
            }
        }
        return -1;
    }
}