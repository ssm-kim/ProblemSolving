import java.io.*;
import java.util.*;

class Node {

    int x;
    int y;
    int cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = dijkstra(0, 0);

            System.out.println("Problem " + idx++ + ": " + dist[n-1][n-1]);
        }
    }

    static int[][] dijkstra(int sx, int sy) {
        // 각 위치까지의 최소 비용 저장
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, INF);

        // 비용 기준 최소힙 (가장 비용이 적은 노트부터 처리)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.cost, n2.cost);
            }
        });

        // 시작점: (0, 0) 시작, 시작점의 비용도 지불
        pq.offer(new Node(sx, sy, board[sx][sy]));
        dist[sx][sy] = board[sx][sy];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int cx = cur.x;
            int cy = cur.y;

            // 가지치기: 이미 더 짧은 경로로 이 위치에 도달했다면 스킵
            if (cur.cost > dist[cx][cy]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                // 새로운 비용 = 현재 비용 + 다음 칸 비용
                int newCost = cur.cost + board[nx][ny];

                // 더 짧은 경로 찾으면 갱신
                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.offer(new Node(nx, ny, cur.cost + board[nx][ny]));
                }
            }
        }
        return dist;
    }
}