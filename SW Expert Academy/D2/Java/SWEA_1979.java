package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1979 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int [][] puzzle = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    puzzle[i][j] = sc.nextInt();
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int row = 0, col = 0;
                for (int j = 0; j < n; j++) {
                    if (puzzle[i][j] == 1) {
                        row += 1;
                    } else {
                        if (row == k) {
                            answer += 1;
                        }
                        row = 0;
                    }

                    if (puzzle[j][i] == 1) {
                        col += 1;
                    } else {
                        if (col == k) {
                            answer += 1;
                        }
                        col = 0;
                    }
                }

                if (row == k) {
                    answer += 1;
                }

                if (col == k) {
                    answer += 1;
                }
            }
            System.out.printf("#%d %d\n", tc+1, answer);
        }
    }
}
