import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 행
        int m = Integer.parseInt(st.nextToken());  // 열
        int r = Integer.parseInt(st.nextToken());  // 회전 횟수
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 반시계 방향
        int halfN = Math.min(n, m) / 2;
        Queue<Integer>[] queue = new LinkedList[halfN];
        for (int i = 0; i < halfN; i++) {
            queue[i] = new LinkedList<>();
        }

        for (int i = 0; i < halfN; i++) {
            // 위쪽 가로줄
            for (int j = i; j < m - i; j++) {
                queue[i].offer(board[i][j]);
            }
            // 오른쪽 세로줄
            for (int j = i + 1; j < n - i; j++) {
                queue[i].offer(board[j][m - 1 - i]);
            }

            // 아래쪽 가로출
            for (int j = m - 2 - i; j >= i; j--) {
                queue[i].offer(board[n - 1 - i][j]);
            }

            // 왼쪽 세로줄
            for (int j = n - 2 - i; j > i; j--) {
                queue[i].offer(board[j][i]);
            }

            int size = queue[i].size();
            int actualRotation = r % size;
            for (int j = 0; j < actualRotation; j++) {
                queue[i].offer(queue[i].poll());
            }
        }

        int[][] temp = new int[n][m];
        for (int i = 0; i < halfN; i++) {

            // 위쪽 가로줄
            for (int j = i; j < m - i; j++) {
                int changeNum = queue[i].poll();
                temp[i][j] = changeNum;
            }
            // 오른쪽 세로줄
            for (int j = i + 1; j < n - i; j++) {
                int changeNum = queue[i].poll();
                temp[j][m - 1 - i] = changeNum;
            }

            // 아래쪽 가로출
            for (int j = m - 2 - i; j >= i; j--) {
                int changeNum = queue[i].poll();
                temp[n - 1 - i][j] = changeNum;
            }

            // 왼쪽 세로줄
            for (int j = n - 2 - i; j > i; j--) {
                int changeNum = queue[i].poll();
                temp[j][i] = changeNum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : temp) {
            for (int col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}