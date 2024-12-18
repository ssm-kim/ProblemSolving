// 신규 버전

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static String S;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = st.nextToken();

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch (S) {
                case "up": up(); break;
                case "down": down(); break;
                case "left": left(); break;
                case "right": right(); break;
            }

            System.out.println("#" + tc);
            StringBuilder sb = new StringBuilder();
            for (int[] row : map) {
                for (int col : row) {
                    sb.append(col).append(" ");
                }
                sb.append("\n");
            }
            sb.delete(sb.length()-1, sb.length());
            System.out.println(sb);
        }
    }

    static void up() {
        for (int i = 0; i < N; i++) {
            // 1. 먼저 모든 숫자들을 빈칸(0) 없이 한쪽으로 붙이기
            for (int j = 0; j < N; j++) {
                if (map[j][i] == 0) {
                    int increase = 0;
                    while (true) {
                        increase++;
                        if (j + increase >= N) break;

                        if (map[j + increase][i] != 0) {
                            map[j][i] = map[j + increase][i];
                            map[j + increase][i] = 0;
                            break;
                        }
                    }  // 현재 값이 0이면 다음 값을 위로 올려야 한다.
                }
            }

            // 2. 붙어있는 같은 숫자들을 합치기(연산)
            for (int j = 1; j < N; j++) {
                if (map[j-1][i] == map[j][i]) {
                    map[j-1][i] = map[j-1][i] * 2;
                    map[j][i] = 0;
                }
            }  // 이전 값과 이후 값이 같다면 이전값 * 2로 갱신 후 이후 값 0으로 갱신

            // 3. 합친 후에 생긴 빈칸(0)들을 다시 한쪽으로 붙이기
            for (int j = 0; j < N; j++) {
                if (map[j][i] == 0) {
                    int increase = 0;
                    while (true) {
                        increase++;
                        if (j + increase >= N) break;

                        if (map[j + increase][i] != 0) {
                            map[j][i] = map[j + increase][i];
                            map[j + increase][i] = 0;
                            break;
                        }
                    }  // 현재 값이 0이면 다음 값을 위로 올려야 한다.
                }
            }
        }
    }

    static void down() {
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (map[j][i] == 0) {
                    int decrease = 0;
                    while (true) {
                        decrease++;
                        if (j - decrease < 0) break;

                        if (map[j - decrease][i] != 0) {
                            map[j][i] = map[j - decrease][i];
                            map[j - decrease][i] = 0;
                            break;
                        }
                    }
                }
            }

            for (int j = N-1; j > 0; j--) {
                if (map[j][i] == map[j-1][i]) {
                    map[j][i] = map[j-1][i] * 2;
                    map[j-1][i] = 0;
                }  // 아래쪽부터 합쳐질 수 있도록
            }

            for (int j = N-1; j >= 0; j--) {
                if (map[j][i] == 0) {
                    int decrease = 0;
                    while (true) {
                        decrease++;
                        if (j - decrease < 0) break;

                        if (map[j - decrease][i] != 0) {
                            map[j][i] = map[j - decrease][i];
                            map[j - decrease][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    static void left() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int increase = 0;
                    while (true) {
                        increase++;
                        if (j + increase >= N) break;

                        if (map[i][j + increase] != 0) {
                            map[i][j] = map[i][j + increase];
                            map[i][j + increase] = 0;
                            break;
                        }
                    }
                }
            }

            for (int j = 1; j < N; j++) {
                if (map[i][j-1] == map[i][j]) {
                    map[i][j-1] = map[i][j-1] * 2;
                    map[i][j] = 0;
                }
            }

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int increase = 0;
                    while (true) {
                        increase++;
                        if (j + increase >= N) break;

                        if (map[i][j + increase] != 0) {
                            map[i][j] = map[i][j + increase];
                            map[i][j + increase] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    static void right() {
        for (int i = 0; i < N; i++) {
            for (int j = N-1; j >= 0; j--) {
                if (map[i][j] == 0) {
                    int decrease = 0;
                    while (true) {
                        decrease++;
                        if (j - decrease < 0) break;

                        if (map[i][j - decrease] != 0) {
                            map[i][j] = map[i][j - decrease];
                            map[i][j - decrease] = 0;
                            break;
                        }
                    }
                }
            }

            for (int j = N-1; j > 0; j--) {
                if (map[i][j] == map[i][j-1]) {
                    map[i][j] = map[i][j-1] * 2;
                    map[i][j-1] = 0;
                }  // 오른쪽부터 합쳐질 수 있도록
            }

            for (int j = N-1; j >= 0; j--) {
                if (map[i][j] == 0) {
                    int decrease = 0;
                    while (true) {
                        decrease++;
                        if (j - decrease < 0) break;

                        if (map[i][j - decrease] != 0) {
                            map[i][j] = map[i][j - decrease];
                            map[i][j - decrease] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
}

// 예전 버전
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Solution {
//
//    static int[][] map, answer;
//    static int n;
//    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("./input.txt"));
//        Scanner sc = new Scanner(System.in);
//
//        int T = sc.nextInt();
//
//        for (int tc = 1; tc <= T; tc++) {
//            n = sc.nextInt();
//            String s = sc.next();
//
//            map = new int[n][n];
//            answer = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    map[i][j] = sc.nextInt();
//                }
//            }
//
//            switch (s) {
//                case "up": up();
//                    break;
//                case "down": down();
//                    break;
//                case "left": left();
//                    break;
//                case "right": right();
//                    break;
//            }
//
//            System.out.println("#" + tc);
//            for (int[] rows : answer) {
//                for (int col : rows) {
//                    System.out.print(col + " ");
//                }
//                System.out.println();
//            }
//        }
//    }
//
//    static void up() {
//        for (int i = 0; i < n; i++) {
//            // 맨 아래는 인덱스는 확인 X
//            for (int j = 0; j < n - 1; j++) {
//                if (map[j][i] == 0 ) continue;
//
//                int row = j + 1;
//
//                // 0이 아닌 값을 찾는다.
//                while (map[row][i] == 0) {
//                    if (row == n-1) break;
//                    row++;
//                }
//
//                // 전부 탐색했는데도 0이 없다면
//                if (map[row][i] == 0) {
//                    continue;
//                }
//
//                // 값 갱신 및 인덱스 변경
//                if (map[j][i] == map[row][i]) {
//                    map[j][i] *= 2;
//                    map[row][i] = 0;
//                    j = row;
//                }
//            }
//        }
//
//        // answer 2차원 배열에 값 복사
//        for (int i = 0; i < n; i++) {
//            int row = 0;
//            for (int j = 0; j < n; j++) {
//                if (map[j][i] != 0) {
//                    answer[row++][i] = map[j][i];
//                }
//            }
//        }
//
//    }
//
//    static void down() {
//        for (int i = 0; i < n; i++) {
//            // 맨 아래는 인덱스는 확인 X
//            for (int j = n - 1; j > 0; j--) {
//                if (map[j][i] == 0 ) continue;
//
//                int row = j - 1;
//
//                // 0이 아닌 값을 찾는다.
//                while (map[row][i] == 0) {
//                    if (row == 0) break;
//                    row--;
//                }
//
//                // 전부 탐색했는데도 0이 없다면
//                if (map[row][i] == 0) {
//                    continue;
//                }
//
//                // 값 갱신 및 인덱스 변경
//                if (map[j][i] == map[row][i]) {
//                    map[j][i] *= 2;
//                    map[row][i] = 0;
//                    j = row;
//                }
//            }
//        }
//
//        // answer 2차원 배열에 값 복사
//        for (int i = 0; i < n; i++) {
//            int row = n - 1;
//            for (int j = n - 1; j >= 0; j--) {
//                if (map[j][i] != 0) {
//                    answer[row--][i] = map[j][i];
//                }
//            }
//        }
//    }
//
//    static void left() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n - 1; j++) {
//                if (map[i][j] == 0) {
//                    continue;
//                }
//
//                int col = j + 1;
//
//                // 0이 있는지 탐색
//                while (map[i][col] == 0) {
//                    if (col == n - 1) break;
//                    col++;
//                }
//
//                // 전부 탐색했는데도 0이 없다면
//                if (map[i][col] == 0) break;
//
//                // 값 변경
//                if (map[i][j] == map[i][col]) {
//                    map[i][j] *= 2;
//                    map[i][col] = 0;
//                    j = col;
//                }
//            }
//        }
//
//        // answer 2차원 배열에 값 복사
//        for (int i = 0; i < n; i++) {
//            int col = 0;
//            for (int j = 0; j < n; j++) {
//                if (map[i][j] != 0) {
//                    answer[i][col++] = map[i][j];
//                }
//            }
//        }
//    }
//
//    static void right() {
//        for (int i = 0; i < n; i++) {
//            for (int j = n - 1; j > 0 ; j--) {
//                if (map[i][j] == 0) {
//                    continue;
//                }
//
//                int col = j - 1;
//
//                // 0이 있는지 탐색
//                while (map[i][col] == 0) {
//                    if (col == 0) break;
//                    col--;
//                }
//
//                // 전부 탐색했는데도 0이 없다면
//                if (map[i][col] == 0) break;
//
//                // 값 변경
//                if (map[i][j] == map[i][col]) {
//                    map[i][j] *= 2;
//                    map[i][col] = 0;
//                    j = col;
//                }
//            }
//        }
//
//        // answer 2차원 배열에 값 복사
//        for (int i = 0; i < n; i++) {
//            int col = n - 1;
//            for (int j = n - 1; j >= 0; j--) {
//                if (map[i][j] != 0) {
//                    answer[i][col--] = map[i][j];
//                }
//            }
//        }
//    }
//}
