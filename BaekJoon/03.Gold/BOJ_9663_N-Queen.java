import java.io.*;

public class Main {

    static int n, answer = 0;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isPass(row, col)) {
                board[row][col] = 1;
                dfs(row + 1);
                board[row][col] = 0;
            }
        }
    }

    static boolean isPass(int sx, int sy) {
        // 세로 위 확인
        for (int nx = sx - 1; nx >= 0; nx--) {
            if (board[nx][sy] == 1) return false;
        }

        // 왼쪽 대각선 위 확인
        int lt = sy;
        for (int nx = sx - 1; nx >= 0; nx--) {
            lt--;

            if (lt < 0) break;
            if (board[nx][lt] == 1) return false;
        }

        // 오른쪽 대각선 위 확인
        int rt  = sy;
        for (int nx = sx - 1; nx >= 0; nx--) {
            rt++;

            if (rt >= n) break;
            if (board[nx][rt] == 1) return false;
        }

        return true;
    }
}