import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int n = sc.nextInt();
			int arr[][] = new int[n][n];

			for (int i = 0; i < n; i++) {
				String str = sc.next();
				for (int j = 0; j < n; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}

			int profit = 0;
			int lt = 0;
			int rt = 1;
			for (int i = 0; i < n; i++) {
				for (int j = (n / 2 + lt); j < (n / 2 + rt); j++) {
					profit += arr[i][j];
				}

				// 1차원 배열의 인덱스가 격자판의 절반을 넘어가면 lt, rt 변수를 반대로 증감해준다.
				if (i < n / 2) {
					lt--;
					rt++;
				} else {
					lt++;
					rt--;
				}
			}
			System.out.println("#" + tc + " " + profit);
		}
	}
}