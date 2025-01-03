import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, cnt;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = 0;  // 총 무리의 갯수

			// 부모 노드 초기화
			parents = new int[N + 1];
			for (int i = 0; i <= N; i++) parents[i] = i;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (find(a) != find(b)) {
					union(a, b);
				}  // 부모가 다를 때만 합집합
			}

			HashSet<Integer> set = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				set.add(find(parents[i]));
			}  // 모든 정점의 최종 루트를 찾아 중복없이 저장
			System.out.println("#" + tc + " " + set.size());
		}
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) return;  // 이미 같은 집합이면 리턴

		parents[aRoot] = bRoot;      // 두 집합을 합침.
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}  // 부모와 내가 같다면 나를 반환 (즉, 내가 루트)

		parents[a] = find(parents[a]);  // 경로 압축: 루트를 찾아 저장
		return parents[a];
	}
}
