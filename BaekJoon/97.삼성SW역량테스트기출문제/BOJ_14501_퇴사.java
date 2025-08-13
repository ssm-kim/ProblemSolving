import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, profit;
    static int[] t, p;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        t = new int[n];  // 각각의 상담은 상담을 완료하는데 걸리는 기간
        p = new int[n];  // 상담을 했을 때 받을 수 있는 금액

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        profit = 0;
        dfs(0, 0);  // 0일차부터 백트래킹 시작
        System.out.println(profit);
    }

    static void dfs(int curDay, int rate) {
        profit = Math.max(profit, rate);  // 최대 수익 갱신

        if (curDay == n) return;  // 퇴사일 도달시 종료

        // 상담 X
        dfs(curDay + 1, rate);

        // 상담 O (퇴사 전에 끝날 수 있을 때만)
        if (curDay + t[curDay] <= n) {
            dfs(curDay+ t[curDay], rate + p[curDay]);
        }
    }
}