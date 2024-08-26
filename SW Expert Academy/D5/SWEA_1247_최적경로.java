import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static int n, m, answer;
	static int[] company, home, customer;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			company = new int[] { sc.nextInt(), sc.nextInt() }; // 회사 좌표
			home = new int[] { sc.nextInt(), sc.nextInt() }; // 집 좌표

			map = new int[n][2]; // 고객들의 좌표
			for (int i = 0; i < n; i++) {
				map[i][0] = sc.nextInt();
				map[i][1] = sc.nextInt();
			}

			// 순열
			answer = Integer.MAX_VALUE;
			visited = new boolean[n];
			customer = new int[n];
			dfs(0);
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void dfs(int depth) {
		if (depth == n) {
			int distance = 0; // 누적 거리

			// 회사에서 첫번째 고객 목적지까지 계산
			int x1 = company[0], y1 = company[1];
			int x2 = map[customer[0]][0], y2 = map[customer[0]][1];
			distance += Math.abs(x1 - x2) + Math.abs(y1 - y2);

			for (int i = 0; i < n - 1; i++) {
				x1 = map[customer[i]][0];
				y1 = map[customer[i]][1];
				x2 = map[customer[i + 1]][0];
				y2 = map[customer[i + 1]][1];
				distance += Math.abs(x1 - x2) + Math.abs(y1 - y2);
			}

			// 마지막 고객 좌표에서 집까지 거리 계산
			x1 = home[0];
			y1 = home[1];
			x2 = map[customer[n - 1]][0];
			y2 = map[customer[n - 1]][1];
			distance += Math.abs(x1 - x2) + Math.abs(y1 - y2);
			
			answer = Math.min(answer, distance);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				customer[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
}
