import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, answer;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<int[]> currentIsland;
    static boolean[][] visited;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 모든 섬 탐색 및 좌표 저장
        ArrayList<ArrayList<int[]>> islands = new ArrayList<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    currentIsland = new ArrayList<>();
                    dfs(i, j);
                    islands.add(currentIsland);
                }
            }
        }

        // 2. 각 섬에서 다른 섬까지 최단거리 탐색
        answer = Integer.MAX_VALUE;
        for (ArrayList<int[]> island : islands) {
            for (int[] pos : island) {
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + dx[i];
                    int ny = pos[1] + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    // 바다와 인접한 테두리에서만 BFS 시작
                    if (board[nx][ny] == 0) {
                        // 현재 섬 방문 처리
                        for (boolean[] row : visited) Arrays.fill(row, false);
                        for (int[] path : island) visited[path[0]][path[1]] = true;

                        bfs(nx, ny);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int distance = pos[2];

            // 다른 섬 도착
            if (board[cx][cy] == 1) {
                answer = Math.min(answer, distance);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny, distance + 1});
            }
        }
    }

    static void dfs(int cx, int cy) {
        currentIsland.add(new int[] {cx, cy});
        visited[cx][cy] = true;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

            if (board[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }
}