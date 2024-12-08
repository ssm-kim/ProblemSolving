import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static final char[] FIX_OPS = {'+' , '-' , '*' , '/'};
    static int N;
    static int minVal, maxVal;
    static int[] numbers, operations;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());  // 숫자 갯수

            st = new StringTokenizer(br.readLine());
            operations = new int[4];

            for (int i = 0; i < 4; i++) {
                operations[i] = Integer.parseInt(st.nextToken());
            }  // + - * / 순서대로 연산자 카드의 개수

            st = new StringTokenizer(br.readLine());
            numbers = new int[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N - 1];
            minVal = Integer.MAX_VALUE;
            maxVal = Integer.MIN_VALUE;

            dfs(0, new char[N - 1]);
            System.out.println("#" + tc + " " + (maxVal - minVal));
        }
    }

    static void dfs(int depth, char[] nextOps) {
        if (depth == N - 1) {
            int sum = numbers[0];
            for (int i = 0; i < N - 1; i++) {
                switch (nextOps[i]) {
                    case '+': sum += numbers[i + 1]; break;
                    case '-': sum -= numbers[i + 1]; break;
                    case '*': sum *= numbers[i + 1]; break;
                    case '/': sum /= numbers[i + 1]; break;
                }
            }
            minVal = Math.min(minVal, sum);
            maxVal = Math.max(maxVal, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                nextOps[depth] = FIX_OPS[i];
                operations[i]--;
                dfs(depth + 1, nextOps);
                operations[i]++;
                nextOps[depth] = ' ';
            }
        }  // 연산자 처리
    }
}