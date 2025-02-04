import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int minCost;
    static int[] plans, costs;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            plans = new int[12];   // 12개월 이용 계획
            costs = new int[4];   // 이용권

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) costs[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) plans[i] = Integer.parseInt(st.nextToken());

            minCost = Integer.MAX_VALUE;
            subset(0, 0);

            System.out.println("#" + tc + " " + minCost);
        }
    }

    static void subset(int month, int totalCost) {
        if (totalCost >= minCost) return;  // 현재 요금이 최소 요금보다 크다면 return

        if (month >= 12) {
            minCost = totalCost;
            return;
        }  // 12개월 초과하면 return

        if (plans[month] == 0) {
            subset(month + 1, totalCost);
            return;
        }  // 이번 달에 계획이 없다면 다음 달로 직행

        // 1일 이용권
        subset(month + 1, totalCost + (plans[month] * costs[0]));

        // 1개월 이용권
        subset(month + 1, totalCost + costs[1]);

        // 3개월 이용권
        subset(month + 3, totalCost + costs[2]);

        // 1년 이용권
        subset(month + 12, totalCost + costs[3]);
    }
}
