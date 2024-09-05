import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	static int n, k, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt(); // 지도 한변의 길이
			k = sc.nextInt(); // 최대 공사 가능 깊이
			map = new int[n][n];
			int maxHeights = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] > maxHeights) { // 가장 높은 봉우리 추출
						maxHeights = Math.max(maxHeights, map[i][j]);
					}
				}
			}

			visited = new boolean[n][n];
			answer = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == maxHeights) { // 가장 높은 봉우리에서만 출발 가능
						dfs(i, j, 1, false);
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	static int dfs(int cx, int cy, int distance, boolean use) {
		visited[cx][cy] = true;
		answer = Math.max(distance, answer);  // 가장 긴 등산로 길 갱신
		
		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];

			// 범위 검사 + 방문 검사
			if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
				continue;
			}
			
			// 높은 지형 -> 낮은 지형만 이동 가능 체크
			if (map[cx][cy] > map[nx][ny]) {
				dfs(nx, ny, distance + 1, use);
			// 깍기를 사용하지 않았고 현재 값이 다음 값-k 보다 크다면 다음 값을 깍아서 진행한다.
			} else if (!use && map[cx][cy] > map[nx][ny] - k) {
				int start = map[nx][ny];  // 초기값을 저장해준다.
				map[nx][ny] = map[cx][cy] - 1;
				dfs(nx, ny, distance + 1, true);
				map[nx][ny] = start;
			}
		}
		
		visited[cx][cy] = false;
		return 0;
	}
}