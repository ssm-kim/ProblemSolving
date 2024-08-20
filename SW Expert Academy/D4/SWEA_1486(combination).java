import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N, B, answer;
	static int[] heights, temp;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 점원들 수
			B = sc.nextInt(); // 선반 높이

			heights = new int[N];

			// 점원들의 키
			for (int i = 0; i < N; i++) {
				heights[i] = sc.nextInt();
			}

			// 조합
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				temp = new int[i+1];
				// depth, 시작 인덱스, 뽑는 개수
				dfs(0, 0, i + 1);
			}
			
			answer -= B;
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void dfs(int depth, int start, int select) {
		// 기저 조건 : depth가 select와 같다면
		if (depth == select) {
			// System.out.println(Arrays.toString(temp));
			int sum = 0;
			for (int x : temp) {
				sum += x;
			}
			// 만들 수 있는 높이가 B 이상인 탑
			if (sum >= B) {
				answer = Math.min(sum, answer);
			}
			return;
		}

		for (int nextIdx = start; nextIdx < N; nextIdx++) {
			temp[depth] = heights[nextIdx];
			dfs(depth + 1, nextIdx + 1, select);
		}
	}
}