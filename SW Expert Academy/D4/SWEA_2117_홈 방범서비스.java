import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {

    static int n, m;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int maxHomes;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            m = sc.nextInt();  // 하나의 집이 지불할 수 있는 비용

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            maxHomes = 0;  // 최대 집 수 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfs(i, j);
                }
            }
            System.out.println("#" + tc + " " + maxHomes);
        }
    }

    static void bfs(int sx, int sy) {

        // 서비스 범위(k)를 1부터 최대 범위(n+1)까지 확장하며 탐색
        for (int k = 1; k <= n + 1; k++) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            queue.offer(new int[]{sx, sy});
            visited[sx][sy] = true;

            int homes = 0;  // 현재 범위 내 집의 수 초기화
            if (map[sx][sy] == 1) homes++;  // 시작점에 집이 있을 경우 집의 수 증가

            int cost = k * k + (k - 1) * (k - 1);   // 현재 운영 비용 계산

            // 현재 범위 k 내에서 모든 노드 탐색
            for (int step = 1; step < k; step++) {  // 서비스 범위 단계적으로 확장
                int size = queue.size();
                for (int i = 0; i < size; i++) {    // 현재 큐에 있는 모든 노드를 탐색
                    int[] pos = queue.poll();
                    int cx = pos[0];
                    int cy = pos[1];

                    for (int d = 0;  d < 4; d++) {
                        int nx = cx + dx[d];
                        int ny = cy + dy[d];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});

                        if (map[nx][ny] == 1) homes++;  // 다음 노드가 집일 경우 집의 수 증가
                    }
                }
            }

            if (homes * m >= cost) {
                maxHomes = Math.max(maxHomes, homes);  // 최대 집 수 갱신
            }
        }
    }
}