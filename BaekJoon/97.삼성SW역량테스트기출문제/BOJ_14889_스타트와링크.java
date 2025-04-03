import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] board;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        combinations(0, 0);

        System.out.println(answer);
    }

    static void combinations(int depth, int start) {
        if (depth == N / 2) {

            int startTeam = 0;
            int linkTeam = 0;

            // 모든 사람 쌍에 대해 시너지 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;  // 자기 자신은 제외

                    // visited가 true인 사람들은 스타트팀
                    if (visited[i] && visited[j]) {
                        startTeam += board[i][j];
                    }

                    // visited가 false인 사람들은 링크팀
                    if (!visited[i] && !visited[j]) {
                        linkTeam += board[i][j];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(startTeam - linkTeam));
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            combinations(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}