import java.io.*;
import java.util.*;

public class Main {

    static int[][] board, temp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 행
        int m = Integer.parseInt(st.nextToken());  // 열
        int r = Integer.parseInt(st.nextToken());  // 연산의 갯수
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            int operator = Integer.parseInt(st.nextToken());  // 수행해야 되는 연산

            switch (operator) {
                case 1:  // 상하 반전
                    topAndBottomChange();
                    break;
                case 2:  // 좌우 반전
                    leftAndRightChange();
                    break;
                case 3:  // 시계 방향 회전
                    rightRotate();
                    break;
                case 4:  // 반시계 방향 회전
                    leftRotate();
                    break;
                case 5:  // N/2×M/2인 4개의 부분 배열 연산
                    rightSeperate();
                    break;
                case 6:  // N/2×M/2인 4개의 부분 배열 연산
                    leftSeperate();
                    break;
            }
        }

        n = board.length;
        m = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void leftSeperate() {
        int n = board.length;
        int m = board[0].length;
        temp = new int[n][m];

        int halfN = n / 2;
        int halfM = m / 2;

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i + halfN][j] = board[i][j];
            }
        }  // 1번 부분 배열 ( 1번 -> 4번 )

        for (int i = halfN; i < n; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][j + halfM] = board[i][j];
            }
        }  // 2번 부분 배열 ( 4번 -> 3번 )

        for (int i = halfN; i < n; i++) {
            for (int j = halfM; j < m; j++) {
                temp[i - halfN][j] = board[i][j];
            }
        }  // 3번 부분 배열 ( 3번 -> 2번 )

        for (int i = 0; i < halfN; i++) {
            for (int j = halfM; j < m; j++) {
                temp[i][j - halfM] = board[i][j];
            }
        }  // 4번 부분 배열 ( 2번 -> 1번 )
        board = temp;
    }

    private static void rightSeperate() {
        int n = board.length;
        int m = board[0].length;
        temp = new int[n][m];

        int halfN = n / 2;
        int halfM = m / 2;

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][j + halfM] = board[i][j];
            }
        }  // 1번 부분 배열 ( 1번 -> 2번 )

        for (int i = 0; i < halfN; i++) {
            for (int j = halfM; j < m; j++) {
                temp[halfN + i][j] = board[i][j];
            }
        }  // 2번 부분 배열 ( 2번 -> 3번 )

        for (int i = halfN; i < n; i++) {
            for (int j = halfM; j < m; j++) {
                temp[i][j - halfM] = board[i][j];
            }
        }  // 3번 부분 배열 ( 3번 -> 4번 )

        for (int i = halfN; i < n; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i - halfN][j] = board[i][j];
            }
        }  // 4번 부분 배열 ( 4번 -> 1번 )
        board = temp;
    }

    private static void leftRotate() {
        // 반시계 방향 왼쪽 회전
        int n = board.length;
        int m = board[0].length;
        temp = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[j][i] = board[i][m - j - 1];
            }
        }
        board = temp;
    }

    private static void rightRotate() {
        // 시계 방향 회전
        int n = board.length;
        int m = board[0].length;
        temp = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[j][n - i - 1] = board[i][j];
            }
        }
        board = temp;
    }

    private static void leftAndRightChange() {
        // 좌우반전
        int n = board.length;
        int m = board[0].length;
        temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][m - j - 1] = board[i][j];
            }
        }
        board = temp;
    }

    static void topAndBottomChange() {
        // 상하반전
        int n = board.length;
        int m = board[0].length;

        temp = new int[n][m];
        for (int i = 0; i < n / 2; i++) {
            int[] front = board[i];
            int[] back = board[n - i - 1];

            temp[i] = back;
            temp[n - i - 1] = front;
        }

        board = temp;
    }
}