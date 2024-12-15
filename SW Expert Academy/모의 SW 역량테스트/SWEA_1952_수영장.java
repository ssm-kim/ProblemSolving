-import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int answer;
    static int[] rates;
    static int[] plans;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            rates = new int[4];
            for (int i = 0; i < 4; i++) {
                rates[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            plans = new int[12];
            for (int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            System.out.println("#" + tc + " " + answer);

        }
    }

    static void dfs(int curMonth, int cost) {
        if (curMonth >= 12) {
            answer = Math.min(answer, cost);
            return;
        }  // 현재 누적 달 수

        if (plans[curMonth] == 0) {
            dfs(curMonth + 1, cost);
        }  // 현재 달에 이용계획이 없다면

        // 1일권
        dfs(curMonth + 1, cost + (plans[curMonth] * rates[0]));

        // 1달권
        dfs(curMonth + 1, cost + rates[1]);

        // 3달권
        dfs(curMonth + 3, cost + rates[2]);

        // 1년권
        dfs(curMonth + 12, cost + rates[3]);
    }
}