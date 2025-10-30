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

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] cables = new int[k];
        for (int i = 0; i < k; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cables);
        System.out.println(binarySearch(cables, n));
    }

    static long binarySearch(int[] cables, int n) {
        long maxLength = 0;  // 가능한 최대 랜선 길이
        long left = 1;       // 최소 길이 (1cm)
        long right = cables[cables.length - 1];  // 최대 길이 (가장 긴 랜선)

        while (left <= right) {
            long mid = (left + right) / 2;  // 현재 시도할 랜선 길이

            // mid 길이로 잘랐을 때 만들 수 있는 랜선 개수
            int curCount = 0;
            for (int cable : cables) {
                curCount += cable / mid;
            }

            // n개 미만이면 길이를 줄여야 함
            if (curCount < n) {
                right = mid - 1;
            }
            // n개 이상이면 더 긴 길이 시도 가능
            else {
                left = mid + 1;
                maxLength = Math.max(maxLength, mid);  // 최대값 갱신
            }
        }

        return maxLength;
    }
}