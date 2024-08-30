import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Solution {

	static int n, m;
	static ArrayList<Integer>[] map;
	static boolean[] visited;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();

			map = new ArrayList[n + 1];

			for (int i = 1; i <= n; i++)
				map[i] = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				map[from].add(to);
				map[to].add(from);
			}

			int cnt = 0;
			visited = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) { // 방문하지 않은 정점이 있다면
					dfs(i);
					cnt++;
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	static void dfs(int cur) {
		visited[cur] = true;

		for (int next : map[cur]) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
	}
}