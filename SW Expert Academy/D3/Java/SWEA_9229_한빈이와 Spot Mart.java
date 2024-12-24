import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, answer;
	static int[] weights;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			weights = new int[N];
			answer = -1;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void dfs(int depth, int start, int sum) {
		if (sum > M) return;  // 가지 치기

		if (depth == 2) {
			answer = Math.max(answer, sum);
			return;
		}  // 최대 2개 선택 가능

		for (int i = start; i < N; i++) {
			dfs(depth + 1, i + 1, sum + weights[i]);
		}
	}
}