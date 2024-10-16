import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N = 9;
	static int[] map = new int[N];
	static int[] idx = new int[7];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		visited = new boolean[N];
		dfs(0, 0, 0);
	}

	static void dfs(int depth, int start, int sum) {
		if (depth == 7) {
			if (sum == 100) {
				for (int i : idx) {
					System.out.println(map[i]);
				}
			}
			return;
		}

		for (int i = start; i < N; i++) {
			idx[depth] = i;
			dfs(depth + 1, i + 1, sum + map[idx[depth]]);
		}
	}
}