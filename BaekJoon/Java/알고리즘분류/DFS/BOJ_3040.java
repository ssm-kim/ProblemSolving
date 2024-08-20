import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class main {

	static int[] arr, ans = new int[7];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./input.txt"));
		Scanner sc = new Scanner(System.in);

		arr = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}

		// 9개 중에 7가지를 뽑는 조합
		// ans : static new int[7] 초기화 상태
		combinations(0, 0, ans);
		
		// System.out.println(Arrays.toString(ans));
		for (int x: ans) {
			System.out.println(x);	
		}
	}

	static boolean combinations(int depth, int start, int[] temp) {
		
		// 기저 조건 : 조합의 깊이(depth)가 7이 되면 현재 조합을 처리 
		if (depth == 7) {
			int sum = 0;
			for (int i : temp) {
				sum += i;
			}
			// 수의 합이 100이 되었으면 정답 추출
			if (sum == 100) {
				return true;
			}
			return false;
		}
		
		for (int next_v = start; next_v < 9; next_v++) {
			// 현재 깊이에서 temp 배열에 현재 인덱스 값을 저장
			temp[depth] = arr[next_v];
			
			// 다음 깊이로 이동하고, 다음 값 추출을 위해 현재 인덱스 +1 
			boolean state =combinations(depth + 1, next_v + 1, temp);
			if (state)  // 결과를 찾았다면 즉시 반환
				return true;
			
			// 인덱스를 사용했으면 다시 복구
			temp[depth] = 0;
		}
		return false;
	}
}