import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R;
    static ArrayList<Integer>[] edges;
    static int[] visited;  // 방문 순서를 기록할 전역 변수

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        // 리스트 초기화
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>(); 
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            // 무방향 그래프
            edges[start].add(end);
            edges[end].add(start);
        }
        
        // 내림차순
        for (int i = 1; i <= N; i++) Collections.sort(edges[i], Collections.reverseOrder());
        visited = new int[N + 1];

        bfs(R);
        for (int i = 1; i <= N; i++) {
            System.out.println(visited[i]);
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int seq = 1;  // 방문 순서 기록
        visited[start] = seq;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : edges[cur]) {
                if (visited[next] == 0) {
                    visited[next] = ++seq;
                    queue.offer(next);
                }
            }
        }
    }
}