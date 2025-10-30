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
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] restArea = new int[n];
        for (int i = 0; i < n; i++) {
            restArea[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(restArea);
        binarySearch(n, m, l, restArea);
    }

    static void binarySearch(int n, int m, int l, int[] restArea) {
        int left = 1;                // 최소 구간 길이
        int right = l - 1;           // 최대 구간 길이

        while (left <= right) {
            int mid = (left + right) / 2;  // 시도할 최대 구간 길이

            // mid 길이로 제한했을 때 필요한 휴게소 개수 계산
            int installCnt = 0;
            int current = restArea[0];

            // 기존 휴게소들 사이 구간 체크
            for (int i = 1; i < n; i++) {
                int next = restArea[i];
                if (next - current > mid) {
                    installCnt += (next - current - 1) / mid;  // 필요한 휴게소 개수
                }
                current = next;
            }

            // 0부터 첫 휴게소까지 구간
            if (restArea[0] > mid) {
                installCnt += (restArea[0] - 1) / mid;
            }

            // 마지막 휴게소부터 끝까지 구간
            int lastSection = l - restArea[n - 1];
            if (lastSection > mid) {
                installCnt += (lastSection - 1) / mid;
            }

            // m개 이하로 충분하면 더 작은 구간 길이 시도
            if (m >= installCnt) {
                right = mid - 1;
            }
            // m개 초과 필요하면 더 큰 구간 길이 필요
            else {
                left = mid + 1;
            }
        }
        System.out.println(left);  // 조건 만족하는 최소 최대구간길이
    }
}