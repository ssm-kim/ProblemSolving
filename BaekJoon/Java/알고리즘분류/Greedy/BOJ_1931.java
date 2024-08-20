import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 회의의 수

		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i][0] = sc.nextInt(); // 시작시간
			arr[i][1] = sc.nextInt(); // 종료시간
		}

		/**
		 * 1. 회의 종료 시간으로 오름차순 정렬
		 * 2. 종료 시간 동일하면 시작 시간으로 오름차순 정렬
		 * 회의실 사용 최대 개수를 구해야 된다.
		 */

		Arrays.sort(arr, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o2, int[] o1) {  // 두번째 파라미터가 첫번째 시작
				// System.out.println(o1[1] + " " + o2[1]);
				// 종료 시간으로 오름차순
				if (o1[1] < o2[1]) {  
					return 1;
				}
				else if (o1[1] > o2[1]) {
					return -1;
				}
				// 만약 종료시간이 동일하면 시작 시간으로 오름차순 정렬
				else {
					return o1[0] > o2[0] ? -1 : 1;
				}
			}
		});

//		for (int[] is : arr) {
//			System.out.println(Arrays.toString(is));
//		}
		
		int maxCnt = 1;
		int end = arr[0][1];
		for (int i = 1; i < n; i++) {
			int nextStart = arr[i][0];
			int nextEnd = arr[i][1];
			// 회의가 끝나는 것과 동시에 다음 회의 시작 가능
			if (nextStart >= end) {
				end = nextEnd;
				maxCnt++;
			}
		}
		System.out.println(maxCnt);
	}
}