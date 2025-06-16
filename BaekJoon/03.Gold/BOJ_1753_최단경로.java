import java.io.*;
import java.util.*;

class Node {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
            "vertex=" + vertex +
            ", weight=" + weight +
            '}';
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> adj;
    static int v, e;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());  // 정점의 개수
        e = Integer.parseInt(st.nextToken());  // 간선의 개수
        int start_v = Integer.parseInt(br.readLine());  // 시작 정점

        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());  // 출발 정점
            int end = Integer.parseInt(st.nextToken());  // 도착 정점
            int w = Integer.parseInt(st.nextToken());  // 가중치

            adj.get(start).add(new Node(end, w));  // (도착 정점, 가중치)
        }

        // 최단 거리 배열 ( 정점 개수 + 1, 0번 인덱스 사용 안함 )
        int[] dist = dijkstra(start_v);

        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
                continue;
            }
            System.out.println(dist[i]);
        }
    }

    static int[] dijkstra(int start_vertex) {
        int[] dist = new int[v + 1];  // 최소 가중치를 저장한다.
        Arrays.fill(dist, INF);  // 최댓값 초기화

        // 최소 힙
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.weight, n2.weight);
            }
        });

        // 출발 정점 담기 (시작 정점, 0)
        pq.offer(new Node(start_vertex, 0));
        dist[start_vertex] = 0;  // 자기 자신은 가중치 0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 현재 정점에서 연결된 모든 정점 탐색
            for (Node next : adj.get(cur.vertex)) {
                int weightSum = cur.weight + next.weight;  // 현재 정점, 다음 정점의 가중치 합
                if (weightSum < dist[next.vertex]) {  // 다음 가중치가 더 크다면 새로 갱신
                    dist[next.vertex] = weightSum;
                    pq.offer(new Node(next.vertex, weightSum));
                }
            }
        }
        return dist;
    }
}