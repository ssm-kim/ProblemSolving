import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] originVisited; // 이미 탐색한 섬들 체크

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        originVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    // 1. 섬 전체 좌표 찾기
                    ArrayList<int[]> islandPath = searchIsland(i, j);

                    // 2. 테두리만 추출 (BFS 시작점 최적화)
                    ArrayList<int[]> outlinePath = new ArrayList<>();
                    for (int[] path : islandPath) {
                        int cx = path[0];
                        int cy = path[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = cx + dx[k];
                            int ny = cy + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                            // 바다와 인접하면 테두리
                            if (map[nx][ny] == 0) {
                                outlinePath.add(new int[] {cx, cy});
                                break;
                            }
                        }
                    }

                    // 3. 각 테두리에서 다른 섬까지 최단거리 탐색
                    for (int[] path : outlinePath) {
                        int cx = path[0];
                        int cy = path[1];

                        int curDistance = searchShortestIsland(cx, cy);
                        answer = Math.min(answer, curDistance);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    // 다른 섬까지 최단 거리 BFS
    static int searchShortestIsland(int sx, int sy) {
        // 현재 섬은 이미 방문 처리
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            visited[i] = Arrays.copyOfRange(originVisited[i], 0 , n);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy, 0});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int distance = pos[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 바다면 다리 놓기 (거리+1)
                if (map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, distance + 1});
                }

                // 다른 섬 도착!
                if (map[nx][ny] == 1) {
                    return distance;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    // 연결된 섬 전체 찾기
    static ArrayList<int[]> searchIsland(int sx, int sy) {
        ArrayList<int[]> path = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        path.add(new int[] {sx, sy});

        originVisited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 0 || originVisited[nx][ny]) continue;

                originVisited[nx][ny] = true;
                path.add(new int[] {nx, ny});
                queue.offer(new int[] {nx, ny});
            }
        }

        return path;
    }
}