import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] requestBudget = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            requestBudget[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());  // 총 예산

        Arrays.sort(requestBudget);

        int left = 1;
        int right = requestBudget[n - 1];  // 상한선 범위: 1 ~ 최대 요청액

        while (left <= right) {
            int adjustBudget = (left + right) / 2;  // 현재 시도할 상한선

            // 상한선으로 배정했을 때의 총 예산 계산
            int totalBudget = 0;
            for (int budget : requestBudget) {
                totalBudget += Math.min(adjustBudget, budget);  // 상한선과 요청액 중 최소값
            }

            // 총 예산이 m 초과하면 상한선을 낮춤
            if (totalBudget > m) {
                right = adjustBudget - 1;
            }
            // m 이하면 더 높은 상한선 시도 가능
            else {
                left = adjustBudget + 1;
            }
        }

        System.out.println(right);  // 조건을 만족하는 최대 상한선
    }
}