import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static int N, answer;
	static int[] home, company, arr;
	static int[][] customer;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			home = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			company = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

			customer = new int[N][2];
			for (int i = 0; i < N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[N];
			arr = new int[N];
			answer = Integer.MAX_VALUE;
			permutation(0);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void permutation(int depth) {
		if (depth == N) {
			int distance = 0;
			// System.out.println("순열 : " + Arrays.toString(arr));
			// 회사에서 첫번째 고객집으로 출발
			int[] start = customer[arr[0]];

			distance += Math.abs(Math.abs(company[0] - start[0]) + Math.abs(company[1] - start[1]));

			for (int i = 1; i < N; i++) {
				int[] next = customer[arr[i]];
				distance += Math.abs(Math.abs(start[0] - next[0]) + Math.abs(start[1] - next[1]));
				start = next;

				if (i == N - 1) {
					distance += Math.abs(Math.abs(next[0] - home[0]) + Math.abs(next[1] - home[1]));
				}  // 마지막 고객집에서 집까지 복귀
			}

			answer = Math.min(distance, answer);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
}
