import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static boolean[] visited;
    static int n, answer;
    static int[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            answer = Integer.MAX_VALUE;
            visited = new boolean[n];
            combination(0, 0);

            System.out.println("#" + tc + " " + answer);
            break;
        }
    }

    static void combination(int depth, int start) {
        if (depth == n / 2) {

            int sumA = 0, sumB = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        sumA += map[i][j] + map[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        sumB += map[i][j] + map[j][i];
                    }
                }
            }  // 시너지 계산

            int diff = Math.abs(sumA - sumB);
            answer = Math.min(answer, diff);
            return;
        }  // (n / 2)개의 요리 생성

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;  // 요리 선택 (true & false)
                combination(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}