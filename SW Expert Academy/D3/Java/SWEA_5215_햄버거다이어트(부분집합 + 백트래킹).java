import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

    static int answer;
    static int N, L;
    static int[] scores;
    static int[] calories;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 재료의 수
            L = Integer.parseInt(st.nextToken());  // 제한 칼로리

            scores = new int[N];
            calories = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MIN_VALUE;
            dfs(0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int depth, int curSum, int curCal) {
        if (curCal > L) {
            return;
        }  // 제한 칼로리 넘어서면 정지

        answer = Math.max(answer, curSum);

        if (depth == N) {
            return;
        }

        // 재료 사용X
        dfs(depth + 1, curSum , curCal);

        // 재료 사용O
        dfs(depth + 1, curSum + scores[depth], curCal + calories[depth]);
    }
}