import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    static int n, answer;
    static int[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            map = new int[n][n];  // 입력 받기

            // 문제 해결
            answer = 0;  // 가능한 배치 수 초기화
            dfs(0);  // 첫 번째 행부터 탐색 시작

            // 출력
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int row) {
        if (row == n) {
            answer++;  // 퀸을 올바르게 배치한 경우
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                map[row][col] = 1;  // 퀸 배치
                dfs(row + 1);  // 다음 행 이동
                map[row][col] = 0;  // 백트래킹 (퀸 제거)
            }
        }
    }

    static boolean isSafe(int row, int col) {

        // 같은 열에 퀸이 있는지 확인
        for (int i = 0; i < row; i++) {
            if (map[i][col] == 1) {
                return false;
            }
        }

        // 왼쪽 대각선에 퀸이 있는지 확인
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (map[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }

        // 오른쪽 대각선에 퀸이 있는지 확인
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < n) {
            if (map[i][j] == 1) {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }
}

