import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M, answer;
    static int[][] map, dp;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }

        answer = dfs(new Point(0, 0));

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();

        System.out.println(answer);
    }

    static int dfs(Point p) {

        if (p.x == N - 1 && p.y == M - 1) {
            return 1;
        }  // 도착점에 도달한 경우

        if (dp[p.x][p.y] != -1) {
            return dp[p.x][p.y];
        }  // 이미 계산된 경우, 저장된 값을 반환

        dp[p.x][p.y] = 0;  // 현재 위치에서의 경로 수 계산 시작

        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            if (map[p.x][p.y] > map[nx][ny]) {
                dp[p.x][p.y] += dfs(new Point(nx, ny));
            }
        }

        return dp[p.x][p.y];
    }
}