import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, l;
    static int[][] map;
    static int[] dx = {1, 2, -1, -2, -1, -2, 1, 2};
    static int[] dy = {2, 1, 2, 1, -2, -1, -2, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            l = Integer.parseInt(br.readLine());

            map = new int[l][l];  // 체스판
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            map[sx][sy] = 1;  // 출발지

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            map[ex][ey] = -1;  // 목적지

            int answer = bfs(sx, sy);
            System.out.println(answer);
        }
    }

    static int bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= l || ny < 0 || ny >= l) {
                    continue;
                }  // 범위 검사

                if (map[nx][ny] == -1) {
                    return map[cx][cy];
                }  // 목적지를 찾음

                if (map[nx][ny] == 0) {
                    map[nx][ny] = map[cx][cy] + 1;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        return 0;
    }
}