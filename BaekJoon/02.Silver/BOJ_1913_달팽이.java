import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int searchNum = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        /**
         * 9 2 3
         * 8 1 4
         * 7 6 5
         */
        int cx = 0;
        int cy = 0;
        int dir = 0;
        int[] answer = new int[2];
        for (int i = n * n; i > 0; i--) {
            board[cx][cy] = i;

            if (board[cx][cy] == searchNum) {
                answer[0] = cx + 1;
                answer[1] = cy + 1;
            }

            cx += dx[dir];
            cy += dy[dir];

            // 범위를 벗어나거나 이미 0이 아니라면 방향을 바꿔준다.
            if (cx < 0 || cx >= n || cy < 0 || cy >= n || board[cx][cy] != 0) {

                // 복구
                cx -= dx[dir];
                cy -= dy[dir];

                dir = (dir + 1) % 4;  // 꺽을 때 처리

                // 방향 전환 이후 이동
                cx += dx[dir];
                cy += dy[dir];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }

        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}