import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		// n개의 수
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[n];
		int[] prefix = new int[n + 1];  // 누적 합
		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			/**
			 * prefix[0 + 1] = prefix[0] + nums[0]
			 * prefix[1 + 1] = prefix[1] + nums[1]
			 * prefix[2 + 1] = prefix[2] + nums[2]
			 * ...
			 * prefix 배열의 이전 값을 활용하여 전체 누적합을 구한다.
			 */
			prefix[i+1] = prefix[i] + nums[i];
		}
		
		// System.out.println(Arrays.toString(prefix));
		
		
		// m개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 시작 구간
			int end = Integer.parseInt(st.nextToken());   // 종료 구간
			
			/**
			 * start와 end는 인덱스로 활용해야 하므로 start만 -1 해준다.
			 * (종료구간 누적합 - 시작구간 누적합)  -> start ~ end 사이의 누적합 추출
			 */
			int sum = prefix[end] - prefix[start-1];
			
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}


