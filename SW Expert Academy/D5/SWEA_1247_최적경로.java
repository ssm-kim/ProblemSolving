import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static boolean[] visited;
	static int[] seq;
	static int[][] map;
	static int minDist = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 2][2];
			visited = new boolean[N];
			minDist = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N + 2; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			seq = new int[N];
			dfs(0, 0);
			System.out.println("#" + tc + " " + minDist);
			break;
		}
	}

	static void dfs(int depth, int curDist) {
		if (curDist > minDist) return;  // 프루닝

		if (depth == N) {
			curDist += Math.abs(map[1][0] - map[seq[N - 1]][0]) + Math.abs(map[1][1] - map[seq[N - 1]][1]);
			minDist = Math.min(minDist, curDist);
			return;
		}  // 마지막 고객 좌표  >  집 거리 계산

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				seq[depth] = i + 2;

				int distance = curDist;
				if (depth == 0) {
					distance = Math.abs(map[0][0] - map[seq[depth]][0]) + Math.abs(map[0][1] - map[seq[depth]][1]);
				}  // 회사  >  처음 고객 좌표
				else {
					distance += Math.abs(map[seq[depth]][0] - map[seq[depth - 1]][0]) + Math.abs(map[seq[depth]][1] - map[seq[depth - 1]][1]);
				}  // 처음 고객 좌표  >  다음 고객 좌표

				dfs(depth + 1, distance);
				visited[i] = false;
			}
		}
	}
}