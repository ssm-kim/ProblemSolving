import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int N, answer;
    static int[] map, formula;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(map));

        // 순열(중복 X)을 구하면 된다.
        formula = new int[N];
        visited = new boolean[N];
        answer = 0;
        dfs(0);
        System.out.println(answer);
    }
    static void dfs(int depth) {
        if (depth == N) {
            int sum = calculator(formula);
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                formula[depth] = map[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
    static int calculator(int[] formula) {
        int curSum = 0;
        for (int i = 0; i < N - 1; i++) {
            curSum += Math.abs(formula[i] - formula[i + 1]);
        }
        return curSum;
    }
}