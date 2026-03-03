import java.io.*;
import java.util.*;

class Node {
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static int n, m;
    static int[] dist, parent;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 양방향 연결
            graph[v].add(new Node(e, w));
            graph[e].add(new Node(v, w));
        }

        // 1번이 슈퍼 컴퓨터이다.
        dijkstra(1);

        int edgeCnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (parent[i] != 0) {
                edgeCnt++;
                sb.append(parent[i] + " " + i + "\n");
            }
        }

        System.out.println(edgeCnt);
        System.out.println(sb.toString());
    }

    static void dijkstra(int start) {
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        parent = new int[n + 1];  // 역추적

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        // 초기화
        dist[start] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 가지치기
            if (current.weight > dist[current.vertex]) continue;

            for (Node next : graph[current.vertex]) {
                int sum = dist[current.vertex] + next.weight;
                if (dist[next.vertex] > sum) {
                    dist[next.vertex] = sum;
                    parent[next.vertex] = current.vertex;
                    pq.offer(new Node(next.vertex, sum));
                }
            }
        }
    }
}