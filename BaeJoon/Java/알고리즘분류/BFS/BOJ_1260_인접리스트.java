package algoClassification.BFS;

import java.io.*;
import java.util.*;

public class BOJ_1260_인접리스트 {

    static int n;
    static ArrayList<ArrayList<Integer>> graph =new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 정점의 개수
        int m = Integer.parseInt(st.nextToken());  // 간선의 개수
        int vertex = Integer.parseInt(st.nextToken());  // 탐색할 정점의 번호

        for (int i = 0; i <= n; i++) {  // 그래프 초기화  ->  0번째 인덱스 사용 X
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {  // 간선 정보 입력
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b); // 무방향 그래프  ->  양방향 연결
            graph.get(b).add(a);
        }



        for (int i = 0; i <= n; i++) {  // 각 정점의 인접 리스트를 정렬 후 정점 번호가 작은 것부터 방문
            Collections.sort(graph.get(i));
        }

        // DFS
        visited = new boolean[n + 1];
        dfs(vertex);
        sb.append("\n");

        // BFS
        visited = new boolean[n + 1]; // 초기화
        bfs(vertex);
        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;      // 첫번째 노드 방문 체크
        while (!queue.isEmpty()) {  // 큐가 비어 있지 않다면
            int v = queue.poll();   // 최상위 노드 pop
            sb.append(v).append(" ");  // 문자열에 현재 노드 추가

            for (int i = 0; i < graph.get(v).size(); i++) {
                int next_v = graph.get(v).get(i);

                if (!visited[next_v]) {  // 방문하지 않았다면  ->  방문 체크, 큐 삽입
                    visited[next_v] = true;
                    queue.offer(next_v);
                }
            }
        }
    }

    static void dfs(int cur_v) {
        visited[cur_v] = true; // 현재 노드 방문
        sb.append(cur_v).append(" ");

        for (int i = 0; i < graph.get(cur_v).size(); i++) {  // 현재 연결된 노드 방문 체크
            int next_v = graph.get(cur_v).get(i);
            if (!visited[next_v])  // 방문하지 않았다면
                dfs(next_v);
        }
    }
}