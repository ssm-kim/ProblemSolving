import java.io.*;
import java.util.*;

public class Main {

    static int M, N;
    static int[][] map, dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;  // dp 초기화
            }
        }

//        for (int[] ints : map) {
//            System.out.println(Arrays.toString(ints));
//        }

        int answer = dfs(0, 0);

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }

        System.out.println(answer);
    }

    static int dfs(int cx, int cy) {
        if (cx == M - 1 && cy == N - 1) {
            return 1;
        }  // 도착점에 도달한 경우

        if (dp[cx][cy] != -1) {
            return dp[cx][cy];
        }  // 이미 계산된 경우, 저장된 값을 반환

        dp[cx][cy] = 0;  // 현재 위치에서의 경로 수 계산 시작

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if (map[cx][cy] > map[nx][ny]) {
                dp[cx][cy] += dfs(nx, ny);
            }
        }
        return dp[cx][cy];
    }
}