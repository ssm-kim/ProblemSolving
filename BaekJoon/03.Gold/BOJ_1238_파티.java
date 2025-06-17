import java.io.*;
import java.util.*;

class Node {
    int vertex;
    int weight;
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n, m, x;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 각각의 마을에 한 명의 학생
        m = Integer.parseInt(st.nextToken());  // 단방향 도로
        x = Integer.parseInt(st.nextToken());  // 파티 장소 (정점)

        List<List<Node>> reverseGraph = new ArrayList<>();
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));         // A -> B 정방향
            reverseGraph.get(end).add(new Node(start, weight));  // B -> A 역방향
        }

        // X(파티 장소)에서 모든 마을로 (정방향 복귀 거리)
        int[] comeTime = dijkstra(graph, x);

        // 모든 마을에서 X(파티 장소)로 (역방향, 가는 거리)
        int[] goTime = dijkstra(reverseGraph, x);

        // 각 학생 왕복 거리 계산 후 최댓값 찾기
        int maxTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, goTime[i] + comeTime[i]);
        }
        System.out.println(maxTime);
    }

    static int[] dijkstra(List<List<Node>> targetGraph, int start_vertex) {
        // 최단 거리 배열 최댓값 초기화
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        // 가중치 기준 최소힙 (가장 가까운 정점부터 처리)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        // 초기값 세팅
        pq.offer(new Node(start_vertex, 0));
        dist[start_vertex] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 가지 치기: 이미 더 짧은 경로로 현재 정점에 도달했다면 스킵
            if (cur.weight > dist[cur.vertex]) continue;

            // 모든 인접 정점 순회
            for (Node next : targetGraph.get(cur.vertex)) {
                int newWeight = cur.weight + next.weight;  // 가중치 합

                // 더 짧은 경로 발견 시 갱신
                if (newWeight < dist[next.vertex]) {
                    dist[next.vertex] = newWeight;
                    pq.offer(new Node(next.vertex, newWeight));
                }
            }
        }
        return dist;
    }
}