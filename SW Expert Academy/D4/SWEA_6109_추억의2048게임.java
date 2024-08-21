import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int[][] map, answer;
    static int n;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            String s = sc.next();

            map = new int[n][n];
            answer = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            switch (s) {
                case "up": up();
                    break;
                case "down": down();
                    break;
                case "left": left();
                    break;
                case "right": right();
                    break;
            }

            System.out.println("#" + tc);
            for (int[] rows : answer) {
                for (int col : rows) {
                    System.out.print(col + " ");
                }
                System.out.println();
            }
        }
    }

    static void up() {
        for (int i = 0; i < n; i++) {
            // 맨 아래는 인덱스는 확인 X
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == 0 ) continue;

                int row = j + 1;

                // 0이 아닌 값을 찾는다.
                while (map[row][i] == 0) {
                    if (row == n-1) break;
                    row++;
                }

                // 전부 탐색했는데도 0이 없다면
                if (map[row][i] == 0) {
                    continue;
                }

                // 값 갱신 및 인덱스 변경
                if (map[j][i] == map[row][i]) {
                    map[j][i] *= 2;
                    map[row][i] = 0;
                    j = row;
                }
            }
        }

        // answer 2차원 배열에 값 복사
        for (int i = 0; i < n; i++) {
            int row = 0;
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0) {
                    answer[row++][i] = map[j][i];
                }
            }
        }

    }

    static void down() {
        for (int i = 0; i < n; i++) {
            // 맨 아래는 인덱스는 확인 X
            for (int j = n - 1; j > 0; j--) {
                if (map[j][i] == 0 ) continue;

                int row = j - 1;

                // 0이 아닌 값을 찾는다.
                while (map[row][i] == 0) {
                    if (row == 0) break;
                    row--;
                }

                // 전부 탐색했는데도 0이 없다면
                if (map[row][i] == 0) {
                    continue;
                }

                // 값 갱신 및 인덱스 변경
                if (map[j][i] == map[row][i]) {
                    map[j][i] *= 2;
                    map[row][i] = 0;
                    j = row;
                }
            }
        }

        // answer 2차원 배열에 값 복사
        for (int i = 0; i < n; i++) {
            int row = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    answer[row--][i] = map[j][i];
                }
            }
        }
    }

    static void left() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                int col = j + 1;

                // 0이 있는지 탐색
                while (map[i][col] == 0) {
                    if (col == n - 1) break;
                    col++;
                }

                // 전부 탐색했는데도 0이 없다면
                if (map[i][col] == 0) break;

                // 값 변경
                if (map[i][j] == map[i][col]) {
                    map[i][j] *= 2;
                    map[i][col] = 0;
                    j = col;
                }
            }
        }

        // answer 2차원 배열에 값 복사
        for (int i = 0; i < n; i++) {
            int col = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    answer[i][col++] = map[i][j];
                }
            }
        }
    }

    static void right() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > 0 ; j--) {
                if (map[i][j] == 0) {
                    continue;
                }

                int col = j - 1;

                // 0이 있는지 탐색
                while (map[i][col] == 0) {
                    if (col == 0) break;
                    col--;
                }

                // 전부 탐색했는데도 0이 없다면
                if (map[i][col] == 0) break;

                // 값 변경
                if (map[i][j] == map[i][col]) {
                    map[i][j] *= 2;
                    map[i][col] = 0;
                    j = col;
                }
            }
        }

        // answer 2차원 배열에 값 복사
        for (int i = 0; i < n; i++) {
            int col = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    answer[i][col--] = map[i][j];
                }
            }
        }
    }
}
