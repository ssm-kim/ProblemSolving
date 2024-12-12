import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, S, answer, check;
    static int[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        check = 0;
        subset(0, 0);
        System.out.println(answer);
    }

    static void subset(int depth, int sum) {
        if (depth == N) {
            if (check++ == 0) return;  // 공집합 처리

            if (sum == S) {
                answer++;
            }
            return;
        }

        subset(depth + 1, sum);
        subset(depth + 1, sum + map[depth]);
    }

}
