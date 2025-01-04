import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static boolean[][][] visited;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        map = new int[N][M];
        visited = new boolean[2][N][M];  // 벽 파괴 여부에 따른 방문 배열
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        answer = Integer.MAX_VALUE;
        bfs(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, 1, 0});  // {x, y, 거리, 벽 부수기 여부}
        visited[0][sx][sy] = true;  // 시작 지점 방문 처리

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int distance = pos[2];
            int destroy = pos[3];  // 벽 파괴 상태

            if (cx == N - 1 && cy == M - 1) {
                answer = distance;
                return;
            }  // 목적지 도착

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }  // 범위 검사

                // 길(0)이고 방문하지 않은 경우
                if (map[nx][ny] == 0 && !visited[destroy][nx][ny]) {
                    visited[destroy][nx][ny] = true;
                    queue.add(new int[] {nx, ny, distance + 1, destroy});
                }   // 벽 부수기 없이 이동

                // 벽(1)이고 파괴할 수 있는 경우
                else if (map[nx][ny] == 1 && destroy == 0) {
                    visited[1][nx][ny] = true;  // 벽을 파괴한 것으로 방문 처리
                    queue.add(new int[] {nx, ny, distance + 1, 1});
                }  // 벽을 부순 후 이동
            }
        }
    }
}