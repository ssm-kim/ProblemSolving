import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, maxProfit = 0;
    static int[] T;  // 상담을 완료하는 데 걸리는 기간
    static int[] P;  // 상담하고 받는 금액

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }  // 각 상담의 소요 기간과 금액 입력받기


        dfs(0, 0);  // ( 현재 날짜, 누적 수익 )
        System.out.println(maxProfit);
    }


    static void dfs(int day, int profit) {
        if (day > N) return;

        // 퇴사일: 최대 수익 갱신 후 종료
        if (day == N) {
            maxProfit = Math.max(profit, maxProfit);
            return;
        }

        // 현재 날짜의 상담 건너뜀.
        dfs(day + 1, profit);

        // 현재 날짜의 상담 진행. ( 상담이 퇴사일 이내에 끝나는 경우만 )
        if (day + T[day] <= N) {
            dfs(day + T[day], profit + P[day]);
        }
    }
}