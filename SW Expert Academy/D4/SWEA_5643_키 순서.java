import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int n, m, edge, answer;
	static boolean[] visited;
	static ArrayList<Integer>[] inMap, outMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			inMap = new ArrayList[n + 1];
			outMap = new ArrayList[n + 1];

			for (int i = 1; i <= n; i++) {
				inMap[i] = new ArrayList<>();
				outMap[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				outMap[a].add(b);
				inMap[b].add(a);
			}

			answer = 0;
			visited = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				int outEdge = dfs(i, outMap); // i번째로 나가는 간선
				Arrays.fill(visited, false);

				int inEdge = dfs(i, inMap);   // i번째로 들어오는 간선
				Arrays.fill(visited, false);

				if (inEdge + outEdge - 1 == n) {
					answer++;
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	static int dfs(int cur, ArrayList<Integer>[] map) {
		visited[cur] = true;  // 현재 방문
		int cnt = 1;
		for (int next : map[cur]) {
			if (!visited[next]) {
				visited[next] = true;
				cnt += dfs(next, map);
			}
		}
		return cnt;
	}
}