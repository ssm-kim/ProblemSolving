import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    static int n, maxMove, room;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            maxMove = 0;
            room = 0;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfs(i, j);
                    init();
                }
            }
            System.out.println("#" + tc + " " + room + " " + maxMove);
        }
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 1, map[sx][sy]});  // 첫번째 방은 방문했으므로 1부터 시작
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int curMove = pos[2];   // 최대 몇개의 방을 이동할 수 있는지?
            int curRoom = pos[3];   // 출발을 시작한 방 번호

            if (curMove > maxMove) {
                maxMove = curMove;
                room = curRoom;
            } else if (curMove == maxMove) {
                room = Math.min(room, curRoom);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] - map[cx][cy] == 1) {  // 정확히 1이 더 커야지 다음 방을 방문할 수 있다.
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, curMove + 1, curRoom});
                }
            }
        }
    }
}
