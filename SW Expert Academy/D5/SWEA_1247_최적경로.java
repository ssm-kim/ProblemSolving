import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최적화 버전
public class Solution {

	static int N, minDistance;
	static int startX, startY, endX, endY;
	static boolean[] visited;
	static int[] seq;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N];       // 회사(출발)와 집(도착)은 제외
			map = new int[N+2][2];
			minDistance = Integer.MAX_VALUE;  // 최단 거리 갱신

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N + 2; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
				if (i == 0) {
					startX = map[i][0];
					startY = map[i][1];
				} else if (i == 1) {
					endX = map[i][0];
					endY = map[i][1];
				}
			}

			seq = new int[N];  // 방문 순서
			perm(0, 0);

			System.out.println("#" + tc + " " + minDistance);
		}
	}

	static void perm(int depth, int curDistance) {
		if (curDistance >= minDistance) return;  // 가지 치기

		if (depth == N) {
			curDistance += Math.abs(map[seq[N-1]][0] - endX) + Math.abs(map[seq[N-1]][1] - endY);
			minDistance = Math.min(minDistance, curDistance);
			return;
		}  // 모든 고객을 방문했으면 마지막 고객에서 집까지의 거리를 더해서 최단거리 갱신

		// 순열 생성 (모든 고객을 방문하는 경우의 수 탐색)
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				seq[depth] = i + 2;  // 현재 depth에 방문할 고객 번호 저장 (회사, 집 제외해서 +2)

				int distance = curDistance;  // 이전까지의 거리 복사 (백트래킹을 위해)
				if (depth == 0) {
					distance += Math.abs(map[seq[depth]][0] - startX) + Math.abs(map[seq[depth]][1] - startY);
				}  // 첫 번째 고객이면 회사에서부터의 거리 계산
				else {
					distance += Math.abs(map[seq[depth]][0] - map[seq[depth-1]][0]) + Math.abs(map[seq[depth]][1] - map[seq[depth-1]][1]);
				}  // 이전 고객에서 현재 고객까지의 거리 계산

				perm(depth + 1, distance);
				visited[i] = false;
			}
		}
	}
}

// 최적화 안된 버전
//public class Solution {
//
//	static int N, minDistance;
//	static int startX, startY, endX, endY;
//	static boolean[] visited;
//	static int[] seq;
//	static int[][] map;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//
//		for (int tc = 1; tc <= T; tc++) {
//			N = Integer.parseInt(br.readLine());
//			visited = new boolean[N];       // 회사(출발)와 집(도착)은 제외
//			map = new int[N+2][2];
//			minDistance = Integer.MAX_VALUE;  // 최단 거리 갱신
//
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N + 2; i++) {
//				map[i][0] = Integer.parseInt(st.nextToken());
//				map[i][1] = Integer.parseInt(st.nextToken());
//				if (i == 0) {
//					startX = map[i][0];
//					startY = map[i][1];
//				} else if (i == 1) {
//					endX = map[i][0];
//					endY = map[i][1];
//				}
//			}
//
//			seq = new int[N];  // 방문 순서
//			perm(0);
//
//			System.out.println("#" + tc + " " + minDistance);
//		}
//	}
//
//	static void perm(int depth) {
//		if (depth == N) {
//			int curDistance = 0;
//			for (int i = 0; i < N; i++) {
//				if (curDistance >= minDistance) return;  // 가지 치기
//
//				if (i == 0) {
//					curDistance += Math.abs(map[seq[i]][0] - startX) + Math.abs(map[seq[i]][1] - startY);
//				}  // 처음 출발지에서 고객 좌표 이동
//				else {
//					curDistance += Math.abs(map[seq[i]][0] - map[seq[i-1]][0]) + Math.abs(map[seq[i]][1] - map[seq[i-1]][1]);
//				}  // 고객 좌표에서 다른 고객 좌표 이동
//			}
//
//			// 마지막 고객 좌표에서 목적지 이동
//			curDistance += Math.abs(map[seq[N-1]][0] - endX) + Math.abs(map[seq[N-1]][1] - endY);
//			minDistance = Math.min(minDistance, curDistance);
//			return;
//		}
//
//		for (int i = 0; i < N; i++) {
//			if (!visited[i]) {
//				visited[i] = true;
//				seq[depth] = i + 2;
//				perm(depth + 1);
//				visited[i] = false;
//			}
//		}
//	}
//}
