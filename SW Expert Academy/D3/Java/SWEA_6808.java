import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int winCount, loseCount;
	static boolean[] visited;
	static int[] GueYoung;
	static int[] InYoung;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {

			// 사용한 카드 확인
			boolean[] selectedCards = new boolean[19];
			
			// 규영이 카드
			GueYoung = new int[9];
			for (int i = 0; i < 9; i++) {
				GueYoung[i] = sc.nextInt();
				selectedCards[GueYoung[i]] = true;
			}

			// 인영이 카드
			InYoung = new int[9];
			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if (!selectedCards[i]) {
					InYoung[idx++] = i;
				}
			}
			
			// 중복 확인 검사
			visited = new boolean[9];
			
			// 경우의 수 초기화
			winCount = 0;
			loseCount = 0;
			
			// 인영이 카드 순열
			permutations(0, new int[9]);

			System.out.println("#" + (tc+1) + " " + winCount + " " + loseCount);
		}
	}

	static void permutations(int depth, int[] inTemp) {
		if (depth == 9) {
			// 계산
			int gueSum = 0, inSum = 0;

			for (int i = 0; i < 9; i++) {
				// 규영이 카드가 높은 숫자라면 총합
				if (GueYoung[i] > inTemp[i]) {
					gueSum += (GueYoung[i] + inTemp[i]); 
				} 
				// 인영 카드가 높은 숫자라면 총합
				else {
					inSum += (GueYoung[i] + inTemp[i]);
				}
			}

			if (gueSum > inSum) {
				winCount++;
			} else {
				loseCount++;
			}

			return;
		}

		// 순열 (중복 X)
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) { // 아직 방문 안 했으면
				visited[i] = true;
				inTemp[depth] = InYoung[i];  // 인영이의 카드 숫자를 넣어야 한다.
				permutations(depth + 1, inTemp);
				visited[i] = false;
			}
		}
	}
}