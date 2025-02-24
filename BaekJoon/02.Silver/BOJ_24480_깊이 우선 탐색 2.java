import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R;
    static ArrayList<Integer>[] edges;
    static int[] visited;
    static int seq = 1;  // 방문 순서를 기록할 전역 변수

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        // 인접 리스트 초기화
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edges[start].add(end);
            edges[end].add(start);
        }  // 양방향 그래프 구성


        for (int i = 1; i <= N; i++) {
            Collections.sort(edges[i], Collections.reverseOrder());
        }  // 각 정점의 인접 리스트를 내림차순으로 정렬

        visited = new int[N + 1];  // 방문 순서 기록 배열

        dfs(R);  // R 정점부터 DFS 시작

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append('\n');
        }  // 각 정점의 방문 순서 출력
        System.out.print(sb);
    }

    static void dfs(int cur) {
        visited[cur] = seq++;  // 현재 정점 방문 처리 및 순서 기록

        // 인접 정점 탐색 (내림차순으로 정렬되어 있음)
        for (int next : edges[cur]) {
            if (visited[next] == 0) {
                dfs(next);  // 재귀적으로 탐색 진행
            }  // 아직 방문하지 않은 정점이면
        }
    }
}