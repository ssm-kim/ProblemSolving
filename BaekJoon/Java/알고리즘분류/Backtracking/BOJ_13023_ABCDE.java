import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, m, answer;
	static ArrayList<Integer>[] map;
	static boolean[] visited;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new ArrayList[n]; // 0번부터 n-1까지 사용하며, 양방향 그래프
		for (int i = 0; i < n; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			map[v1].add(v2);
			map[v2].add(v1);
		}

		visited = new boolean[n];
		answer = 0;

		for (int i = 0; i < n; i++) {
			if (answer != 1) {
				dfs(i, 1);
			}
		}
		System.out.println(answer);
	}

	static void dfs(int cur_v, int cnt) {
		if (cnt == 5) { // 5명이 최대
			answer = 1;
			return;
		}

		visited[cur_v] = true;
		for (int next_v : map[cur_v]) {
			if (!visited[next_v]) {
				dfs(next_v, cnt + 1);
			}
		}
		visited[cur_v] = false;
	}
}