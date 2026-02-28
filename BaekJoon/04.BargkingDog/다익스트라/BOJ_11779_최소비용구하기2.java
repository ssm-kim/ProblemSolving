import java.io.*;
import java.util.*;

class Node {
    int vertex, cost;

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}

public class Main {

    static int n, m;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());  // 도시 개수
        m = Integer.parseInt(br.readLine());  // 버스 개수

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node (end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    static void dijkstra(int start, int end) {
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];  // 경로 역추적용
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 최소 힙: 비용이 작은 노드부터 처리
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 이미 더 짧은 경로로 방문한 노드면 스킵
            if (current.cost > dist[current.vertex]) continue;

            // 인접 노드 탐색
            for (Node next : graph[current.vertex]) {
                int newCost = dist[current.vertex] + next.cost;

                // 더 짧은 경로 발견 시 갱신 + 부모 기록
                if (dist[next.vertex] > newCost) {
                    dist[next.vertex] = newCost;
                    parent[next.vertex] = current.vertex;
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }

        // 경로 역추적: end → start 방향으로 parent 따라감
        ArrayList<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != 0) {
            path.add(cur);
            cur = parent[cur];
        }

        System.out.println(dist[end]);
        System.out.println(path.size());
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }
}