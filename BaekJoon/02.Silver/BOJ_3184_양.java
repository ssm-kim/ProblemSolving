import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int R, C;
    static boolean[][] visited;
    static char[][] map;
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '#' && !visited[i][j]) {
                    bfs(i, j);
                } // 필드라면
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

    static void bfs(int sx, int sy) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { sx, sy });
        visited[sx][sy] = true;

        int ram = 0, wolf = 0;
        if (map[sx][sy] == 'v') wolf++;
        if (map[sx][sy] == 'o') ram++;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 검사 + 방문 검사
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] != '#') {
                    if (map[nx][ny] == 'v') wolf++;
                    if (map[nx][ny] == 'o') ram++;

                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny });
                } // 울타리가 아니면 방문 체크하고 큐에 다음 좌표값 넣기
            }
        }

        if (wolf >= ram) answer[1] += wolf;  // 늑대 수가 양 이상이면 잡아먹는다.
        else answer[0] += ram;               // 양의 수가 더 많으면 늑대를 쫓아낸다.
    }
}
