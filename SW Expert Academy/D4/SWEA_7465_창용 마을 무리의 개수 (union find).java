import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Solution {

	static int n, m;
	static int[] parents;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			n = sc.nextInt();
			m = sc.nextInt();

			make();
			for (int i = 0; i < m; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				union(from, to);
			}

			int answer = 0;
			for (int i = 1; i <= n; i++) {
				if (parents[i] == i) {
					answer++;
				}
			}

			// System.out.println(Arrays.toString(parents));

			System.out.println("#" + t + " " + answer);
		}
	}

	static void make() {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}

	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		// System.out.println(aRoot + " " + bRoot);
		
		if (aRoot == bRoot)
			return;

		parents[bRoot] = aRoot;
		return;
	}
}