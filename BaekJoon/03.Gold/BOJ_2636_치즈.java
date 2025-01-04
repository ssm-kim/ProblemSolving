import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastCheese = 0;

        while (true) {
            int cheese = countCheese();
            if (cheese == 0) break;

            lastCheese = cheese;
            bfs();
            time++;
        }
        System.out.println(time + "\n" + lastCheese);
    }

    static int countCheese() {
        int cnt = 0;
        for (int[] row : map) {
            for (int col : row) {
                if (col == 1) cnt++;
            }
        }
        return cnt;
    }  // 치즈 카운팅

    static void bfs() {
        visited = new boolean[N][M];
        LinkedList<int[]> queue = new LinkedList<>();
        ArrayList<int[]> meltList = new ArrayList<>();

        // 외부 공기에서 시작
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }

                if (map[nx][ny] == 1) {
                    meltList.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        for (int[] pos : meltList) {
            map[pos[0]][pos[1]] = 0;
        }  // 치즈 녹이기
    }
}