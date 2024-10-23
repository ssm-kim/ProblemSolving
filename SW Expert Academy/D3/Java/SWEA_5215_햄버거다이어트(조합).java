import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, L, answer;
    static int[] scores;
    static int[] calories;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 재료의 수
            L = Integer.parseInt(st.nextToken());  // 제한 칼로리
            answer = 0;

            scores = new int[N];
            calories = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            combinations(0, 0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void combinations(int depth, int start, int scoreSum, int calSum) {
        if (calSum > L) return;

        answer = Math.max(answer,scoreSum);

        if (depth == N) {
            return;
        }  // 최대 깊이 도달시

        for (int i = start; i < N; i++) {

            combinations(depth + 1, i + 1,scoreSum + scores[i], calSum + calories[i]);
        }
    }
}