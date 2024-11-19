import java.io.*;

public class Main {
    static final int SELECT = 2;
    static int K;
    static String[] originWords, newWords;
    static boolean[] visited;
    static String answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            answer = "";
            originWords = new String[K];
            for (int j = 0; j < K; j++) {
                originWords[j] = br.readLine();
            }

            newWords = new String[SELECT];
            visited = new boolean[K];

            permutations(0);

            System.out.println(answer.isEmpty() ? "0" : answer);
        }
    }

    static void permutations(int depth) {
        if (!answer.isEmpty()) return;  // 가지치기: answer를 이미 찾았으면 종료

        if (depth == SELECT) {
            StringBuilder sb = new StringBuilder();
            sb.append(newWords[0]).append(newWords[1]);
            String attach = sb.toString();

            boolean check = true;
            int len = attach.length();

            for (int i = 0; i < len / 2; i++) {
                if (attach.charAt(i) != attach.charAt(len - i - 1)) {
                    check = false;
                    break;  // 팰린드롬이 아니면 즉시 종료
                }
            }

            if (check) answer = attach;
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                newWords[depth] = originWords[i];
                permutations(depth + 1);
                visited[i] = false;
            }
        }
    }
}