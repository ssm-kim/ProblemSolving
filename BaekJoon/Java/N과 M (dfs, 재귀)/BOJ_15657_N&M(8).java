import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Main {

	static int n, m;
	static int[] arr, temp;
	static boolean[] visited;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);
		// 중복 조합
		temp = new int[m];
		visited = new boolean[n];
		dfs(0, 0);

	}

	static void dfs(int depth, int start) {
		if (depth == m) {
			for (int i : temp) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}

		for (int next = start; next < n; next++) {
			temp[depth] = arr[next];
			dfs(depth + 1, next);
		}
	}
}