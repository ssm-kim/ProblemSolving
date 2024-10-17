import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] map;
	static int[] prefix;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());  // 수의 갯수
		M = Integer.parseInt(st.nextToken());  // 합을 구해야하는 횟수
		map = new int[N + 1];
		prefix = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			prefix[i + 1] = prefix[i] + map[i + 1];
		}  // 구간합 구하기

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(prefix[end] - prefix[start-1]).append("\n");
		}
		System.out.println(sb);
	}
}