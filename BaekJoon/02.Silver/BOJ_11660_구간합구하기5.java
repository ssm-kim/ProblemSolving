import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] prefix;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		prefix = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}  // 입력 받기

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				prefix[i][j + 1] = prefix[i][j] + map[i][j + 1];
			}
		}  // 각 행 별로 구간 합 구하기

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
			int answer = 0;

			for (int row = x1 - 1; row < x2; row++) {
				answer += prefix[row][y2] - prefix[row][y1 - 1];
			}  // (x1 - 1) ~ x2 까지의 행만큼 반복 이후, 현재 행의 (y1 - 1) - y2 값을 빼서 구간 합 추출
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}


/**  2차원 누적합
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
 */