import java.io.*;
import java.util.*;

public class Main {

    static int n, r, q;
    static boolean[] visited;
    static int[] subQueryCnt;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[n + 1];
        subQueryCnt = new int[n + 1];
        dfs(r);  // 각 정점마다 서브쿼리 갯수를 미리 다 구해놓는다.

        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());

            System.out.println(subQueryCnt[u]);
        }
    }

    static void dfs(int cur) {
        visited[cur] = true;
        subQueryCnt[cur] = 1;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(next);
                subQueryCnt[cur] += subQueryCnt[next];
            }
        }
    }
}