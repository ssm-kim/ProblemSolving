import java.io.*;

// 8방향 탐색 !!!
public class Solution {

    static int N, answer;
    static int[][] map;
    static int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
    static int[] dy = {-1, 1, 1, -1, -1, 1, 0, 0};

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
        }

        for (int col = 0; col < N; col++) {
            if (safety(row, col)) {
                map[row][col] = 1;
                dfs(row + 1);
                map[row][col] = 0;
            }
        }
    }

    static boolean safety(int row, int col) {

        for (int i = 0; i < 8; i++) {
            int nx = row;
            int ny = col;

            while (true) {
                nx += dx[i];
                ny += dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break;
                }  // 범위 검사

                if (map[nx][ny] == 1) {
                    return false;
                }  // 이미 퀸이 있다면
            }  // 각 방향에서 범위를 벗어날 때까지 진행 방향에 퀸이 있는지 확인
        }
        return true;
    }
}



//import java.io.*;
//
//public class Solution {
//
//    static int N, answer;
//    static int[][] map;
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//
//        for (int tc = 1; tc <= T; tc++) {
//            N = Integer.parseInt(br.readLine());
//            map = new int[N][N];
//            answer = 0;
//
//            dfs(0);
//            System.out.println("#" + tc + " " + answer);
//        }
//    }
//
//    static void dfs(int row) {
//        if (row >= N) {
//            answer++;
//            return;
//        }  // 행의 길이가 N 이상이면 중지
//
//        for (int col = 0; col < N; col++) {
//            if (safety(row, col)) {
//                map[row][col] = 1;
//                dfs(row + 1);
//                map[row][col] = 0;
//            }  // 놓을 수 있는 퀸 위치이면 놓고 백트래킹
//        }
//    }
//
//    static boolean safety(int row, int col) {
//
//        // 세로 체크
//        for (int i = 0; i < row; i++) {
//            if (map[i][col] == 1) return false;
//        }
//
//        // 왼쪽 위 대각선 체크
//        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
//            if (map[i][j] == 1) return false;
//        }
//
//        // 오른쪽 위 대각선 체크
//        for (int i = row-1, j = col+1; i >= 0 && j < N; i--, j++) {
//            if (map[i][j] == 1) return false;
//        }
//
//        return true;
//    }
//}