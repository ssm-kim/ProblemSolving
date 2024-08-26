import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static int n, m, answer, cnt;
	static int[] height, tmp;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			height = new int[n];

			for (int i = 0; i < n; i++) {
				height[i] = sc.nextInt();
			}

			// 최대 무게 합 (부분 집합)
			answer = -1;
			tmp = new int[n];
			dfs(0, 0);
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void dfs(int depth, int sum) {
		// m 그램 초과 시 return;
		if (sum > m) {
			return;
		}

		// 2개를 선택할 때 마다
		if (cnt == 2) {
			answer = Math.max(sum, answer);
			return;
		}

		// 최대 깊이 도달 시 return;
		if (depth == n) {
			return;
		}


		dfs(depth + 1, sum);
		cnt++;
		dfs(depth + 1, sum + height[depth]);
		cnt--;
	}
}
