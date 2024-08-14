import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			int n = sc.nextInt(); // 표의 크기
			int m = sc.nextInt(); // 합을 구해야 하는 횟수

			int[][] arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int ans = Integer.MIN_VALUE;
			// m * m 파리채 영역만큼 처리할 수 있으니 row, col에 각각 (- m + 1)만큼 해준다.
			for (int i = 0; i < n - m + 1; i++) {
				for (int j = 0; j < n - m + 1; j++) {
					int sum = 0;
					// m * m 파리채 영역 시작  i, j 기준 
					for (int k = i; k < m + i; k++) {
						for (int l = j; l < m + j; l++) {
							sum += arr[k][l];
						}
					}
					ans = Math.max(ans, sum);
				}
			}
			System.out.println("#" + (tc+1) + " " + ans);
		}
	}
}
