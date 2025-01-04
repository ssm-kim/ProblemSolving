import java.io.*;
import java.util.*;

public class Main {

    static final int SIZE = 5;
    static int[][] map = new int[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        loop: for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                int callNum = Integer.parseInt(st.nextToken());

                search(callNum);
                cnt++;
                if (checkBingo()) break loop;  // 빙고 완성
            }
        }
        System.out.println(cnt);
    }

    static boolean checkBingo() {
        int curLine = 0;
        int rightDiag = 0, leftDiag = 0;
        for (int i = 0; i < SIZE; i++) {
            int row = 0, col = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 0) row++;  // 행 검사
                if (map[j][i] == 0) col++;  // 열 검사

                if (i == j && map[i][j] == 0) rightDiag++;            // 우하단 대각선 검사
                if (i == j && map[i][SIZE - j - 1] == 0) leftDiag++;  // 좌하단 대각선 검사
            }
            // 행과 열 중 빙고를 만들었다면
            if (row == SIZE) curLine++;
            if (col == SIZE) curLine++;
        }

        if (rightDiag == SIZE) curLine++;
        if (leftDiag == SIZE) curLine++;

        return curLine >= 3;
    }

    static void search(int callNum) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == callNum) {
                    map[i][j] = 0;
                    return;
                }  // 방문 완료.
            }
        }
    }
}
