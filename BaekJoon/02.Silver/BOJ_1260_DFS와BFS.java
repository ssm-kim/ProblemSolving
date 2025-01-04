import java.io.*;
import java.util.*;

public class Main {

    static int N, M, V;
    static boolean[] visited;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            map[i] = new ArrayList<>();
        }  // 리스트 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 양방향 연결
            map[start].add(end);
            map[end].add(start);
        }

        for (int i = 0; i < N + 1; i++) {
            Collections.sort(map[i]);
        }  // 정렬

        visited = new boolean[N + 1];
        dfs(V);
        System.out.println();

        visited = new boolean[N + 1];
        bfs(V);
    }

    static void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        System.out.print(start + " ");

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    System.out.print(next + " ");
                }
            }
        }
    }

    static void dfs(int cur) {
        visited[cur] = true;
        System.out.print(cur + " ");

        for (int next : map[cur]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}