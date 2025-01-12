import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
	int x;
	int y;
	long weight;

	public Node(int x, int y, long weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node nd) {
		return Long.compare(this.weight, nd.weight);
	}
}

public class Solution {

	static long answer;
	static int N;
	static double E;
	static int[] parents;
	static ArrayList<int[]> island;
	static ArrayList<Node> map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// 입력 받기
			N = Integer.parseInt(br.readLine());
			island = new ArrayList<>();
			map = new ArrayList<>();
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			E = Double.parseDouble(br.readLine());  // 환경 부담 세율

			// 1. 섬들의 좌표 정보 입력 받기
			for (int i = 0; i < N; i++) {
				int a = Integer.parseInt(st1.nextToken());
				int b = Integer.parseInt(st2.nextToken());
				island.add(new int[]{a, b});  // [x좌표, y좌표] 형태로 저장
			}

			// 2. 모든 섬 쌍에 대해 거리 계산
			for (int i = 0; i < N; i++) {
				int startX = island.get(i)[0];
				int startY = island.get(i)[1];
				for (int j = 0; j < N; j++) {
					if (i != j) {
						int endX = island.get(j)[0];
						int endY = island.get(j)[1];
						long a = startX - endX;
						long b = startY - endY;
						long diff = (a * a) + (b * b);  // 두 섬 간의 거리 제곱 계산 (피타고라스)
						map.add(new Node(i + 1, j + 1, diff));  // 간선 정보 저장
					}
				}
			}

			// 3. 크루스칼 알고리즘 수행을 위한 간선 정렬
			Collections.sort(map);  // 비용이 적은 순으로 정렬

			// 4. Union-Find 초기화 (부모 노드 초기화)
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) parents[i] = i;

			// 5. 크루스칼 알고리즘으로 최소 신장 트리 구축
			answer = 0;
			int cnt = 0;  // 섬 갯수
			for (Node node : map) {
				if (cnt == N - 1) break;  // MST 완성시 즉시 종료

				int start = node.x;
				int end = node.y;
				long dist = node.weight;

				if (find(start) != find(end)) {
					union(start, end);  // 두 섬을 연결
					answer += dist;     // 거리 비용 누적
					cnt++;
				}  // 사이클이 형성되지 않는 경우만
			}
			System.out.println("#" + tc + " " + Math.round(answer * E));
		}
	}

	// find 연산
	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);  // 경로 압축
	}

	// union 연산
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) return;

		parents[bRoot] = aRoot;  // bRoot를 aRoot에 붙인다.
	}
}
