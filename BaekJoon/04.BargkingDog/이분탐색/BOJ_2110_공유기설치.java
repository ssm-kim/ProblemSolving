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
        int c = Integer.parseInt(st.nextToken());
        int[] home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);
        System.out.println(binarySearch(home, n, c));
    }

    static int binarySearch(int[] home, int n, int c) {
        int left = 1;                       // 최소 간격
        int right = home[n - 1] - home[0];  // 최대 간격 (양끝 거리)

        while (left <= right) {
            int mid = (left + right) / 2;   // 시도할 최소 간격

            // mid 간격으로 몇 개 설치 가능한지 그리디 체크
            int installCnt = 1;             // 첫 집에 무조건 설치
            int start = home[0];            // 마지막 설치 위치
            for (int i = 1; i < n; i++) {
                if (home[i] - start >= mid) {
                    start = home[i];        // 설치 위치 갱신
                    installCnt++;           // 설치 개수 증가
                }
            }

            // c개 이상 설치 가능하면 더 큰 간격 시도
            if (installCnt >= c) {
                left = mid + 1;
            }
            // c개 미만이면 더 작은 간격으로 조정
            else {
                right = mid - 1;
            }
        }
        return right;  // 조건 만족하는 최대 간격
    }
}