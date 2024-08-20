import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N, B, answer;
	static int[] heights;

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

			answer = Integer.MAX_VALUE;
			// depth, 시작 인덱스, 뽑는 개수
			dfs(0, 0);
			
			answer -= B;
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void dfs(int depth, int sum) {
		// 기저 조건 : depth가 select와 같다면
		if (sum >= B) {
			answer = Math.min(sum, answer);
			return;
		}
		
		// 만들 수 있는 높이가 B 이상인 탑		
		if (depth == N) {
			return;
		}
		
		dfs(depth + 1, sum + heights[depth]);
		dfs(depth + 1, sum);
	}
}