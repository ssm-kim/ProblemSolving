import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, -1`, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int notSee = 0, see = 0;
        visited = new boolean[2][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[0][i][j]) {
                    notSee++;
                    bfs(i, j, 0);
                }  // 색약 X
                if (!visited[1][i][j]) {
                    see++;
                    bfs(i, j, 1);
                }  // 색약 O
            }
        }
        System.out.println(notSee + " " + see);
    }

    static void bfs(int sx, int sy, int flag) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        visited[flag][sx][sy] = true;
        char curColor = map[sx][sy];  // 현재 색깔

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[flag][nx][ny]) {
                    continue;
                }  // 범위 검사 + 방문 검사

                if (flag == 0 && curColor == map[nx][ny]) {
                    visited[flag][nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }  // (색약 X) 현재 컬러와 다음 컬러가 같다면

                if (flag == 1) {
                    if (curColor == 'R' || curColor == 'G') {
                        if (map[nx][ny] == 'R' || map[nx][ny] == 'G') {
                            visited[flag][nx][ny] = true;
                            queue.offer(new int[] {nx, ny});
                        }
                    }
                    else if (curColor == 'B' && map[nx][ny] == 'B') {
                        visited[flag][nx][ny] = true;
                        queue.offer(new int[] {nx, ny});
                    }
                }  // (색약 O) 현재 컬러와 다음 컬러가 같다면
            }
        }
    }
}