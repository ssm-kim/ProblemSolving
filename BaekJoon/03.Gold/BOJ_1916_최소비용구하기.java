import java.io.*;
import java.util.*;

class Node {
    int vertex;  // 정점
    int cost;    // 비용
    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph;
    static int n, m;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());  // 도시(정점)의 개수
        m = Integer.parseInt(br.readLine());  // 버스(간선)의 개수

        // 인접 리스트로 그래프 초기화 (1-indexed)
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // 간선 정보 입력 (방향 그래프)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());  // 출발 정점
            int end = Integer.parseInt(st.nextToken());    // 도착 정점
            int cost = Integer.parseInt(st.nextToken());   // 가중치 (비용)

            graph.get(start).add(new Node(end, cost));  // 출발 -> 도착 (비용)
        }

        // 출발지와 도착지 입력
        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());  // 초기 출발 정점
        int endCity = Integer.parseInt(st.nextToken());    // 구하고자 하는 도착 정점

        int result = dijkstra(startCity, endCity);
        System.out.println(result);
    }

    static int dijkstra(int startCity, int endCity) {
        // 최단 거리 배열 초기화
        int[] dist = new int [n + 1];
        Arrays.fill(dist, INF);

        // 우선순위 큐 (비용 기준 최소힙)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.cost, n2.cost);
            }
        });

        // 시작점 초기화
        pq.offer(new Node(startCity, 0));
        dist[startCity] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();  // 가장 비용이 적은 도시 선택

            // 현재 비용이 다음 정점 가는 비용보다 더 크면 가지치기
            if (cur.cost > dist[cur.vertex]) continue;

            // 현재 도시에서 인접한 모든 도시 방문
            for (Node next : graph.get(cur.vertex)) {
                int newCost = cur.cost + next.cost;  // 새로운 경로의 총 비용

                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost;  // 최단 거리 갱신
                    pq.offer(new Node(next.vertex, newCost));  // 큐에 추가
                }
            }
        }
        return dist[endCity];  // 출발지에서 도착지까지 최소 비용
    }
}