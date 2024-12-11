import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int blue = 0;
    static int white = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void dfs(int row, int col, int size) {
        // 계산
        int curSum = 0;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (map[i][j] == 1) {
                    curSum++;
                }
            }
        }

        // 기저 조건1 : 현재 영역 합이 0일 때
        if (curSum == 0) {
            white++;
            return;
        }

        // 기저 조건2 : 현재 영역 합이 size^2일 때
        if (curSum == size * size) {
            blue++;
            return;
        }

        int half = size / 2;

        dfs(row, col, half);  // 좌상단
        dfs(row, col + half, half);  // 우상단
        dfs(row + half, col, half);  // 좌하단
        dfs(row + half, col + half, half);  // 우하단

    }
}