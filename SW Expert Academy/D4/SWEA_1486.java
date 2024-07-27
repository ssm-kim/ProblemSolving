package D4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SWEA_1486 {

    static int N, B, minVal;
    static int[] height;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            N = sc.nextInt(); // N명의 점원
            B = sc.nextInt(); // 탑의 높이 B
            height = new int[N];  // 점원들의 키
            minVal = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                height[i] = sc.nextInt();
            }

            // 점원의 키 누적 합이 탑의 높이보다 크다면
            // (탑의 높이 - 누적합) 뺀 값의 최소 값을 diff 변수에 갱신.

            // 점원의 키 누적 합은 dfs로 구한다.

            dfs(0, 0);
            System.out.println("#" + (tc+1) + " " + minVal);
            break;
        }
    }
    static void dfs (int depth, int sum) {
        // System.out.println(sum);
        if (sum >= B) {  // 점원들 키의 합이 B 이상이면
            int diff = sum - B;
            if (minVal > diff) {  // 최소값 갱신
                System.out.println(diff);
                minVal = diff;
            }
            return;
        }

        if (depth == N) {  // 깊이가 N에 도달하면 중지
            return;
        }

        dfs(depth + 1, sum + height[depth]);
        dfs(depth + 1, sum);
    }
}
