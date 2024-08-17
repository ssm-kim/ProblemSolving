import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, 0);

        System.out.print(sb);
        br.close();
    }

    public static void dfs(int depth, int num) {
        if (depth == N) {
            sb.append(num).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int next = num * 10 + i;
            if (isPrime(next)) {
                dfs(depth + 1, next);
            }
        }
    }

    static boolean isPrime(int n) {
        // 2보다 작은 수는 소수가 아니다.
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            // n이 i로 나누어 떨어지면 n은 소수가 아니다.
            if (n % i == 0) {
                return false;
            }
        }

        // 위의 조건에 해당하지 않으면 n은 소수이다.
        return true;
    }

}