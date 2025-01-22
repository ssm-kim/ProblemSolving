import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] cables;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());  // 이미 가지고 있는 랜선 개수
        N = Integer.parseInt(st.nextToken());  // 필요한 랜선의 개수
        cables = new int[K];
        for (int i = 0; i < K; i++) cables[i] = Integer.parseInt(br.readLine());

        Arrays.sort(cables);

        System.out.println(binarySearch());
    }

    static long binarySearch() {
        // 1. 탐색 범위 설정
        long left = 1;
        long right = cables[K - 1];
        long answer = 0;

        // 2. 이진 탐색 시작
        while (left <= right) {
            long mid = (left + right) / 2;

            // 3. 각 랜선을 mid 길이로 잘라서 나오는 개수 계산
            long cnt = 0;  // 현재 길이로 잘랐을 때 나오는 랜선 개수
            for (int i = 0; i < K; i++) {
                cnt += cables[i] / mid;
            }

            // 4. 개수에 따라 범위 조정
            if (cnt >= N) {        // 목표보다 많이 나오면
                answer = mid;      // 답 저장
                left = mid + 1;    // 더 긴 길이 시도
            } else {               // 목표보다 적게 나오면
                right = mid - 1;   // 더 짧은 길이 시도
            }
        }
        return answer;
    }
}