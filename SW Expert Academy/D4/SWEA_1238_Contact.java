import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int answer, depth;
    static int N, start;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            graph = new ArrayList[101];
            for (int i = 0; i < 101; i++) graph[i] = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);  // 단방향 그래프
            }

            visited = new boolean[101];
            depth = Integer.MIN_VALUE;
            answer = Integer.MIN_VALUE;

            bfs(start);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void bfs(int start) {
        LinkedList<int[]> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(new int[]{start, 0});  // [현재 노드, 현재 깊이] 형태로 저장

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cur_v = pos[0];     // 현재 노드 번호
            int curDepth = pos[1];  // 현재 노드의 깊이

            if (curDepth > depth) {
                depth = curDepth;
                answer = Integer.MIN_VALUE;
            }  // 더 깊은 레벨 발견 시 최대값 초기화

            answer = Math.max(answer, cur_v);  // 현재 깊이에서 가장 큰 노드 번호 갱신

            for (int next_v : graph[cur_v]) {
                if (!visited[next_v]) {
                    visited[next_v] = true;
                    queue.offer(new int[]{next_v, curDepth + 1});
                }  // 다음 노드는 현재보다 1만큼 깊음
            }
        }
    }
}