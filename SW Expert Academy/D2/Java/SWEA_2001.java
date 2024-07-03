package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_2001 {
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc < T + 1; tc++) {
            int n = sc.nextInt(), m = sc.nextInt();
            int [][] board = new int[n][n];
            int max = 0;  // 파리 죽인 최댓값

            for (int i = 0; i < n; i++) {  // 입력값 받기
                for (int j = 0; j < n; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n - m + 1; i++) {
                for (int j = 0; j < n - m + 1; j++) {

                    int cnt = 0;  // m * m 범위에서 파리를 잡은 총 갯수

                    for (int k = i; k < i+m; k++) {
                        for (int l = j; l < j+m; l++) {
                            cnt += board[k][l];
                        }
                    }

                    if (cnt > max) {
                        max = cnt;
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, max);
        }
    }
}
