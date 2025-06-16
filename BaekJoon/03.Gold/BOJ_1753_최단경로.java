import java.io.*;
import java.util.*;

class Node {
    int vertex;  // 목적지 정점 번호
    int weight;  // 간선의 가중치

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph;  // 인접 리스트로 그래프 표현
    static int v, e;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());  // 정점의 개수
        e = Integer.parseInt(st.nextToken());  // 간선의 개수
        int start_v = Integer.parseInt(br.readLine());  // 시작 정점

        // 인접 리스트 초기화 (1-indexed 사용)
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());  // 출발 정점
            int end = Integer.parseInt(st.nextToken());    // 도착 정점
            int w = Integer.parseInt(st.nextToken());      // 간선 가중치

            graph.get(start).add(new Node(end, w));  // 방향 그래프 : start -> end (가중치 w)
        }

        // 다익스트라 알고리즘 실행 (단일 출발점에서 모든 정점까지의 최단경로)
        int[] dist = dijkstra(start_v);

        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");  // 도달 불가
                continue;
            }
            System.out.println(dist[i]);  // 최단 거리
        }
    }

    static int[] dijkstra(int start_vertex) {
        // 최단거리 배열 초기화
        int[] dist = new int[v + 1];  // 시작점에서 각 정점까지의 최단 거리
        Arrays.fill(dist, INF);  // 최댓값 초기화

        // 우선순위 큐 (가중치 기준 최소힙)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.weight, n2.weight);
            }
        });

        // 시작점 설정
        pq.offer(new Node(start_vertex, 0));
        dist[start_vertex] = 0;  // 시작점까지의 거리는 0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();  // 가장 가까운 미처리 정점 선택

            // 중복 처리 방지: 이미 더 짧은 경로로 처리된 경우 스킵 (가지 치기)
            if (cur.weight > dist[cur.vertex]) continue;

            // 현재 정점의 모든 인접 정점 탐색
            for (Node next : graph.get(cur.vertex)) {
                int newDist = cur.weight + next.weight;  // 새로운 경로의 총 거리

                // 더 짧은 경로를 발견한 경우 갱신
                if (newDist < dist[next.vertex]) {
                    dist[next.vertex] = newDist;  // 최단거리 갱신
                    pq.offer(new Node(next.vertex, newDist));  // 큐에 추가하여 나중에 탐색
                }
            }
        }
        return dist;
    }
}