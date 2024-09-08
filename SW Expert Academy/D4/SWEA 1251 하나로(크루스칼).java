import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	static int[] parents;
	static int V, E;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			V = sc.nextInt(); // 섬의 개수

			int[] x = new int[V];
			int[] y = new int[V];

			for (int i = 0; i < V; i++) {
				x[i] = sc.nextInt();
			}
			
			for (int i = 0; i < V; i++) {
				y[i] = sc.nextInt();
			}

			// 환경 부담 세율
			double rate = sc.nextDouble();

			/// 간선 개수와 가중치 확인
			E = V * (V - 1) / 2;

			Edge[] edges = new Edge[E];
			int idx = 0;
			for (int i = 0; i < V; i++) {
				for (int j = i + 1; j < V; j++) {
					// 유클리드 거리 계산 후 환경 부담 세율을 곱하면 가중치가 나온다.
					long xx = Math.abs(x[i] - x[j]);
					long yy = Math.abs(y[i] - y[j]);
					double distance = Math.sqrt((Math.pow(xx, 2) + Math.pow(yy, 2)));
					double weight = rate * (distance * distance);
					edges[idx++] = new Edge(i, j, weight);
				}
			}

			// 크루스칼 알고리즘 가중치별로 오름차순 정렬
			Arrays.sort(edges);

			// 유니온 파인드
			make();
			double minCost = 0.0;
			int cnt = 0;  // 섬 갯수
			for (int i = 0; i < E; i++) {
				int a = edges[i].start;
				int b = edges[i].end;
				
				if (findParents(a) != findParents(b)) {
					minCost += edges[i].weight;
					union(a, b);
					cnt++;
				}
				
				if (cnt == V-1) {
					break;
				}
				
			}

			System.out.printf("#%d %.0f\n", tc, minCost);
		}
	}

	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = -1;
		}
	}

	static int findParents(int a) {
		if (parents[a] < 0) {
			return a;
		}
		// 경로 압축
		return parents[a] = findParents(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findParents(a);
		int bRoot = findParents(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}
}

class Edge implements Comparable<Edge> {
	int start, end;
	double weight;

	public Edge(int start, int end, double weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.weight, o.weight);
	}
}