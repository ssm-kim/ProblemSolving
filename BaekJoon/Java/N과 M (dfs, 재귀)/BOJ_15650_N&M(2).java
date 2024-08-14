import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		// 조합
		arr = new int[m];
		combinations(0, 1);

	}

	static void combinations(int depth, int start) {
		// 기저 조건 : depth가 m이 되었을 때 현재 깊이까지 저장된 arr 배열 값 출력
		if (depth == m) {
			for (int x : arr) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		}

		for (int next_v = start; next_v < n + 1; next_v++) {
			/**
			 *	만약 리스트를 사용한다면 사용한 값 제거해야 함.
			 *	배열은 값을 덮어쓰므로 그냥 둬도 됨. 
			 */
			// 현재 깊이에 숫자 선택
			arr[depth] = next_v;  
			
			// depth 증가 시 다음 시작 인덱스는 현재 선택한 숫자 다음부터
			combinations(depth + 1, next_v + 1);
		}
	}
}