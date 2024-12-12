import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static long N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Long.parseLong(br.readLine());
            long cnt = 0;

            while (N != 2) {
                double p = Math.sqrt(N);  // 루트 앤이 정수인지 아닌지 판단
                if ((long) p == p) {
                    cnt++;
                    N = (long) p;
                } else {  // 루트 정수가 아닐 때
                    long a = (long) Math.sqrt(N) + 1;
                    long b = a * a;
                    cnt = (long) (b - N) + cnt;
                    N = b;
                }
            }
            System.out.println("#" + tc + " " + cnt);
            if (tc == 2) break;
        }
    }
}