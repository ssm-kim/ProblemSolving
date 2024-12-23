import java.io.*;

public class Main {

    static String N;
    static char[] origin;
    static boolean[] visited;
    static int len, answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        len = N.length();
        origin = new char[len];

        for (int i = 0; i < len; i++) {
            origin[i] = N.charAt(i);
        }

        visited = new boolean[len];
        dfs(0, new char[len]);
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    static void dfs(int depth, char[] seq) {
        if (depth == len) {
            int res = 0;
            for (char c : seq) {
                res = res * 10 + (c - '0');
            }

            if (res > Integer.parseInt(N)) {
                answer = Math.min(answer, res);
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[depth] = origin[i];
                dfs(depth + 1, seq);
                visited[i] = false;
            }
        }
    }
}