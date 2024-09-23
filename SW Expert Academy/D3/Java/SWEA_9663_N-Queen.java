import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            answer = 0;
            dfs(0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int row) {
        if (row == N) {
            answer++;
            return;
        }  // N행까지 다 채우면 계산

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                map[row][col] = 1;
                dfs(row + 1);
                map[row][col] = 0;  // 백트래킹
            }
        }
    }

    static boolean isSafe(int cx, int cy) {
        // 8방향 탐색

        for (int i = 0; i < 8; i++) {
            int nx = cx;
            int ny = cy;

            while (true) {
                nx += dx[i];
                ny += dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break;
                }  // 범위 벗어나면 종료

                if (map[nx][ny] == 1) {
                    return false;
                }  // 공격 가능
            }
        }
        return true;  // 모든 방향 안전함.
    }
}