package D2;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA_1961 {

    static int n;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            int [][] board = new int [n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int [][] rotate_90 = rotate(board);
            int [][] rotate_180 = rotate(rotate_90);
            int [][] rotate_270 = rotate(rotate_180);

            System.out.println("#" + (tc+1));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(rotate_90[i][j]);
                }
                System.out.print(" ");

                for (int j = 0; j < n; j++) {
                    System.out.print(rotate_180[i][j]);
                }
                System.out.print(" ");

                for (int j = 0; j < n; j++) {
                    System.out.print(rotate_270[i][j]);
                }
                System.out.println();
            }
        }
    }
    public static int[][] rotate(int[][] board) {
        int [][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = board[n-j-1][i];
            }
        }
        return tmp;
    }
}
