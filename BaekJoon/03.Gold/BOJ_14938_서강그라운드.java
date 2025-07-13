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
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 지역의 개수
        int m = Integer.parseInt(st.nextToken());  // 예은이 수색 범위
        int r = Integer.parseInt(st.nextToken());  // 길의 개수

        // 각 지역별 아이템 개수 저장
        st = new StringTokenizer(br.readLine());
        int[] items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 그래프
            graph.get(start).add(new Node(end, weight));
            graph.get(end).add(new Node(start, weight));
        }


        // 모든 지역을 시작점으로 하여 최대 아이템 수집량 계산
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // // 현재 지역(i)에서 모든 지역까지의 최단거리 계산
            int[] dist = dijkstra(n, i);

            // 수색 범위(m) 내에 있는 지역들의 아이템 수집
            int curItemsCnt = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) {  // 수색 범위 내에 있으면 해당 지역 아이템 추가
                    curItemsCnt += items[j];
                }
            }

            // 최대 아이템 수집량 갱신
            answer = Math.max(answer, curItemsCnt);
        }
        System.out.println(answer);
    }

    static int[] dijkstra(int n, int startVertex) {
        // 거리 배열 무한대로 초기화
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        // 최소 힙 우선순위 큐 (거리 기준 정렬)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.weight, n2.weight);
            }
        });

        // 시작점 초기화
        pq.offer(new Node(startVertex, 0));
        dist[startVertex] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 처리된 정점이면 스킵 (최적화)
            if (cur.weight > dist[cur.vertex]) {
                continue;
            }

            // 현재 정점과 연결된 모든 인접 정점 탐색
            for (Node next : graph.get(cur.vertex)) {
                int weightSum = cur.weight + next.weight;  // 새로운 경로의 총 거리

                // 더 짧은 경로를 발견했다면 갱신
                if (weightSum < dist[next.vertex]) {
                    dist[next.vertex] = weightSum;
                    pq.offer(new Node(next.vertex, weightSum));
                }
            }
        }
        return dist;
    }
}