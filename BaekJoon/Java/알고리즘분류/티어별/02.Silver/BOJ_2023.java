import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        System.out.println(sb);
    }

    static void dfs(int depth, int num) {
        if (depth == N) {
            sb.append(num).append("\n");
            return;
        }  // 신기한 소수 + N자리 숫자

        for (int i = 1; i <= 9; i++) {
            int decisionNum = num * 10 + i;

            if (isPrime(decisionNum)) {
                dfs(depth + 1, decisionNum);
            }
        }
    }

    static boolean isPrime(int decisionNum) {
        if (decisionNum == 1) return false;  // 처음 파라미터 값을 넘겨받을 때 체크

        for (int i = 2; i <= Math.sqrt(decisionNum); i++) {
            if (decisionNum % i == 0) {
                return false;
            }  // i로 나누어 떨어지면 소수가 아니다.
        }
        return true;
    }
}