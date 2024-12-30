import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int answer;
    static int[] costs;
    static int[] plans;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs = new int[4];
            for (int i = 0; i < 4; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            plans = new int[12];
            for (int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int month, int allCost) {
        if (month >= 12) {
            answer = Math.min(answer, allCost);
            return;
        }  // 기저 조건  ->  12개월 이상일 때

        if (plans[month] == 0) {
            dfs(month + 1, allCost);
            return;
        }  // 가지 치기

        // 1일권 사용
        dfs(month + 1, allCost + (plans[month] * costs[0]));

        // 1달권 사용
        dfs(month + 1, allCost + costs[1]);

        // 3달권 사용
        dfs(month + 3, allCost + costs[2]);

        // 1년권 사용
        dfs(month + 12, allCost + costs[3]);
    }
}