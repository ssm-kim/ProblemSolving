import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int K;
    static int[] map;
    static LinkedList<Integer> lottoNums = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            map = new int[K];
            for (int i = 0; i < K; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);  // 조합 구하기
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int start) {
        if (depth == 6) {
            for (int lottoNum : lottoNums) {
                sb.append(lottoNum).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            lottoNums.offer(map[i]);
            dfs(depth + 1, i + 1);
            lottoNums.pollLast();
        }
    }
}