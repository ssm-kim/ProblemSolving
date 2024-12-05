import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int answer = 0;

			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = row.charAt(j) - '0';
				}
			}

			int lt = N / 2;
			int rt = N / 2 + 1;
			for (int i = 0; i < N; i++) {
				for (int j = lt; j < rt; j++) {
					answer += map[i][j];
				}
				if (i  >= N / 2) {
					lt++;
					rt--;
				} else {
					lt--;
					rt++;
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}