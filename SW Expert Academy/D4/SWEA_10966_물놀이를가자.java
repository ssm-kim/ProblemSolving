import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    static int N, M, minDist;
    static char[][] map;
    static int[][] dist;  // 물(W)에서 각 위치까지의 최단거리를 저장할 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            dist = new int[N][M];
            Queue<Node> queue = new LinkedList<>();

            // dist 배열 -1로 초기화 (미방문 표시)
            for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'W') {
                        queue.add(new Node(i, j));
                        dist[i][j] = 0;
                    }  // W 위치를 시작점으로 큐에 추가
                }
            }

            bfs(queue);
            minDist = 0;
            for (int[] row : dist) {
                for (int col : row) {
                    minDist += col;
                }
            }

            System.out.println("#" + tc + " " + minDist);
        }
    }

    static void bfs(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node nd = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[nd.x][nd.y] + 1;
                    queue.offer(new Node(nx, ny));
                }  // 미방문 칸이면 거리 갱신하고 큐에 추가
            }
        }
    }
}
