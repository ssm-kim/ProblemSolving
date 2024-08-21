import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int answer = 0;
		int[][] area = new int[100][100]; // 전체 넓이

		for (int i = 0; i < n; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;

			// 가로 세로 크기가 10인 정사각형을 만든다.
			int rEnd = x + 10;
			int cEnd = y + 10;
			
			for (int r = x; r < rEnd; r++) {
				for (int c = y; c < cEnd; c++) {
					if (area[r][c] == 0) {  // 방문 검사
						area[r][c] = 1;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);		
	}
}