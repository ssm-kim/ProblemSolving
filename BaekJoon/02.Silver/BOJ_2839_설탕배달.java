import java.io.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int maxFiveKg = N / 5;  // 5kg 봉지의 최대 간으 개수
		int answer = -1;        // 기본값 -1로 설정 (불가능할 때)

		for (int fiveCnt = maxFiveKg; fiveCnt >= 0; fiveCnt--) {
			int remain = N - (5 * fiveCnt);   // 5kg 봉지 사용 후 남은 무게

			if (remain % 3 == 0) {            // 남은 무게가 3kg 봉지로 나누어 떨어지면
				int threeCnt = remain / 3;    // 3kg 봉지 개수
				answer = fiveCnt + threeCnt;  // 5kg, 3kg 봉지 합산
				break;
			}
		}
		System.out.println(answer);
	}
}