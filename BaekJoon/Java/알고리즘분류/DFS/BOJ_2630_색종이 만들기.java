import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Main {

	static int N, white, blue, arr[][];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		dfs(0, 0, N);

		System.out.println(white + "\n" + blue);

	}

	static void dfs(int row, int col, int size) {
		// 영역 검사
		int sum = 0;
		int rowEnd = row + size;
		int colEnd = col + size;
		for (int i = row; i < rowEnd; i++) {
			for (int j = col; j < colEnd; j++) {
				sum += arr[i][j];
			}
		}

		// 기저 조건1 : 현재 영역 합이 0이면 흰색
		if (sum == 0) {
			white++;
			return;
		}
		// 기저 조건2 : 현재 영역 합이 size^2이면 파란색
		if (sum == size * size) {
			blue++;
			return;
		}

		// 영역을 절반으로 줄인다.
		int half = size / 2;
		// 최대 N^2 만큼 반복
		dfs(row, col, half); // 좌상단
		dfs(row, col + half, half); // 우상단

		dfs(row + half, col, half); // 좌하단
		dfs(row + half, col + half, half); // 우하단
	}
}
