import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 조합 수를 계산하기 위해 DFS를 호출합니다.
            int ans = dfs(0, 0);  // 현재 인덱스, 현재 선택된 개수

            System.out.println(ans);
        }
    }

    static int dfs(int index, int cur_size) {
        // 현재 선택된 개수가 n과 같으면 유효한 조합입니다.
        if (cur_size == n) {
            return 1;
        }

        int count = 0;

        // 현재 인덱스부터 시작하여 재귀적으로 다음 인덱스에 대해 조합을 계산합니다.
        for (int next_n = index; next_n < m; next_n++) {
            int combi = dfs(next_n + 1, cur_size + 1);
            count += combi;
        }

        return count;
    }
}