import java.io.*;
import java.util.*;

public class Main {

    static int n, m, ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];

        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int start, int cur_size) {
        // 현재 선택된 요소의 개수가 m과 같으면 조합 완성
        if (cur_size == m) {
            ans += 1; // 조합의 개수 증가
            return;
        }

        for (int next_v = start; next_v < n; next_v++) {
            if (!visited[next_v]) {
                visited[next_v] = true;
                dfs(next_v + 1, cur_size + 1);  // 다음 인덱스와 현재 선택된 개수를 증가시켜서 dfs 호출
                visited[next_v] = false;  // 백트래킹
            }
        }
    }

}