import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int n;
    static int answer;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 문제 해결
        arr = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        visited = new boolean[n];
        dfs(0, 0);

        // 출력
        System.out.println(answer);
    }

    // 조합
    static void dfs(int depth, int start) {
        if (depth == n / 2) {
            int teamStart = 0;
            int teamLink = 0;

            // 각 팀의 능력치 계산
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        teamStart += map[i][j] + map[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        teamLink += map[i][j] + map[j][i];
                    }
                }
            }

            // 능력치 차이 계산 및 최소값 갱신
            int diff = Math.abs(teamStart - teamLink);
            answer = Math.min(answer, diff);
            return;
        }

        // n개 중 2개를 선택하는 조합
        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}

