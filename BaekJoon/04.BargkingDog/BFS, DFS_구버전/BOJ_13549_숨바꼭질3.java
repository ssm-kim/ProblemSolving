import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int x, cost;

    public Node(int x, int cost) {
        this.x = x;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node{" +
            "x=" + x +
            ", cost=" + cost +
            '}';
    }
}

public class Main {

    static int n, k;
    static int[] dist;
    static int[] dx = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 다익스트라
        dijkstra();

    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.cost, n2.cost);
            }
        });
        dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Node(n, 0));
        dist[n] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 목적지 조착
            if (cur.x == k) {
                System.out.println(cur.cost);
                return;
            }

            // 가지 치기: 이미 처리된 노드
            if (cur.cost > dist[cur.x]) {
                continue;
            }

            for (int i = 0; i < 3; i++) {
                // 1. 순간이동 (비용: 0)
                if (i == 2) {
                    int nx = cur.x * dx[i];
                    if (nx < 0 || nx >= 100001) continue;

                    if (dist[nx] > cur.cost) {
                        dist[nx] = cur.cost;
                        pq.offer(new Node(nx, cur.cost));
                    }
                }
                // 2. 걷기 (비용: 1)
                else {
                    int nx = cur.x + dx[i];
                    int newCost = cur.cost + 1;
                    if (nx < 0 || nx >= 100001) continue;

                    if (dist[nx] > newCost) {
                        dist[nx] = newCost;
                        pq.offer(new Node(nx, newCost));
                    }
                }
            }
        }
    }
}