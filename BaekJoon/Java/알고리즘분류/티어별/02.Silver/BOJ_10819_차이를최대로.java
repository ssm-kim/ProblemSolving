import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, answer = Integer.MIN_VALUE;
	static int[] arr, seq;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		seq = new int[N];
		visited = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		permutations(0);
		
		System.out.println(answer);
	}

	static void permutations(int depth) {
		if (depth == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(arr[seq[i]] - arr[seq[i + 1]]);
			}	
			
			if (sum > answer) answer = sum;

			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				seq[depth] = i;
				permutations(depth + 1);
				visited[i] = false;
			}
		}
	}
}