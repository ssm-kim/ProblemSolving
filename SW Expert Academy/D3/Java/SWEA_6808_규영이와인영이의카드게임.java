import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] gu;
	static int[] in;
	static int[] round;
	static int[] answer;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			gu = new int[9];
			in = new int[9];
			boolean[] cardCheck = new boolean[19];

			for (int i = 0; i < 9; i++) {
				gu[i] = Integer.parseInt(st.nextToken());
				cardCheck[gu[i]] = true;
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!cardCheck[i]) {
					in[idx++] = i;
				}
			}

			visited = new boolean[9];
			round = new int[9];
			answer = new int[2];
			permutation(0);

			System.out.println("#" + tc + " " + answer[0] + " " + answer[1]);
		}
	}

	static void permutation(int depth) {
		if (depth == 9) {
			int guSum = 0;
			int inSum = 0;

			for (int i = 0; i < 9; i++) {
				int guCard = gu[i];
				int inCard = round[i];

				if (guCard > inCard) {
					guSum += (guCard + inCard);
				}  // 규영이 카드 숫자가 높다면
				else {
					inSum += (guCard + inCard);
				}  // 인영이 카드 숫자가 높다면
			}

			// 무승부 무시
			if (guSum > inSum) {
				answer[0]++;
			} else {
				answer[1]++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				round[depth] = in[i];  // 각 라운드마다 인영이 카드를 넣는다
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
}