import java.io.*;
import java.util.*;

public class Main {

    static boolean isCycle;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNumber = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            int treeCnt = 0;
            visited = new boolean[n + 1];
            for (int start = 1; start <= n; start++) {
                if (visited[start]) continue;

                isCycle = false;
                dfs(start, start);

                if (!isCycle) treeCnt++;
            }

            if (treeCnt == 0) {
                System.out.println("Case " + caseNumber + ": No trees.");
            }
            else if (treeCnt == 1) {
                System.out.println("Case " + caseNumber + ": There is one tree.");
            }
            else {
                System.out.println("Case " + caseNumber + ": A forest of " + treeCnt + " trees.");
            }
            caseNumber++;
        }
    }

    static void dfs(int cur, int parent) {
        visited[cur] = true;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(next, cur);
            }
            else if (next != parent) {
                isCycle = true;
                return;
            }
        }
    }
}