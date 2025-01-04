import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static ArrayList<Integer>[] map;
	static boolean[] visited;
	static boolean search;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		N = Integer.parseInt(st.nextToken());  // 사람의 수
		M = Integer.parseInt(st.nextToken());  // 친구 관계의 수
		map = new ArrayList[N];                // 0번부터 시작
		for (int i = 0; i < N; i++) map[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 친구 관계이므로 양방향 !!
			map[from].add(to);
			map[to].add(from);
		}

		visited = new boolean[N];

		// 각 정점을 시작점으로 DFS 수행
		for (int i = 0; i < N; i++) {
			if (search) break;  // 이미 찾았다면 더 찾을 필요 없음

			dfs(0, i);
		}
		System.out.println(search ? 1 : 0);
	}

	static void dfs(int depth, int cur_v) {
		if (depth == 4) {
			search = true;
			return;
		}  // 5명이 일렬로 연결되었다면 (depth가 4면 5번째 사람)

		visited[cur_v] = true;
		for (int next_v : map[cur_v]) {
			if (!visited[next_v]) {
				dfs(depth + 1, next_v);
			}
		}
		visited[cur_v] = false;
	}
}