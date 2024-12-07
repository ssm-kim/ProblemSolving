import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, B, answer;
    static int[] S;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            S = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            combinations(0, 0, 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void combinations(int depth, int start, int height) {
        if (height >= B) {
            answer = Math.min(answer, height - B);
            return;
        }  // 가지치기

        if (depth == N) {
            return;
        }

        for (int i = start; i < N; i++) {
            combinations(depth + 1, i + 1, height + S[i]);
        }
    }
}