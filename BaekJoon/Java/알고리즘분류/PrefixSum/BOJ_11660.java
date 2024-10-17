import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./input.txt"));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 표의 크기
		int m = sc.nextInt(); // 합을 구해야 하는 횟수

		// n개의 수
		int[][] nums = new int[n + 1][n + 1];
		int[][] prefix = new int[n + 1][n + 1]; // 누적 합

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				nums[i][j] = sc.nextInt();
				// 누적 합 구하기
				prefix[i][j] = nums[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
			}
		}

//		for (int[] num : prefix) {
//			System.out.println(Arrays.toString(num));
//		}

		// m개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			// 시작 구간 좌표
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();

			// 종료 구간 좌표
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();

			// 구간 합 계산
			int sum = prefix[x2][y2] - prefix[x1 - 1][y2] -
					  prefix[x2][y1 - 1] + prefix[x1 - 1][y1 - 1];
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
