import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static boolean[][][] visited; // [벽부수기상태][행][열] - 핵심 상태 관리
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        visited = new boolean[2][N][M];
        answer = Integer.MAX_VALUE;
        bfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { sx, sy, 0, 1 }); // {x, y, 벽부수기여부, 거리}
        visited[0][sx][sy] = true; // 벽 안부순 상태로 시작

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int isDestroy = pos[2]; // 0: 벽 안부숨, 1: 벽 부숨
            int distance = pos[3];

            // 목적지 도달 - 최단거리 확정
            if (cx == N - 1 && cy == M - 1) {
                answer = distance;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 빈 공간 이동 - 현재 벽부수기 상태 유지
                if (map[nx][ny] == 0 && !visited[isDestroy][nx][ny]) {
                    visited[isDestroy][nx][ny] = true;
                    queue.add(new int[] { nx, ny, isDestroy, distance + 1 });
                }
                // 벽 부수기 - 아직 벽을 안부쉈을 때만 가능
                else if (map[nx][ny] == 1 && isDestroy == 0 && !visited[1][nx][ny]) {
                    visited[1][nx][ny] = true;
                    queue.add(new int[] { nx, ny, 1, distance + 1 });
                }
            }
        }
    }
}