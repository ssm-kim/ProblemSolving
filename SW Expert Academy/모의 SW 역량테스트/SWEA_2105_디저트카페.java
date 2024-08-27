import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    static int n, answer, cnt;
    static int[][] dir = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static int[][] map;
    static boolean[][] visited;
    static HashSet<Integer> set;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            map = new int[n][n];
            set = new HashSet<>();
            visited = new boolean[n][n];
            answer = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    set.clear();
                    visited = new boolean[n][n];

                    set.add(map[i][j]);
                    visited[i][j] = true;   // 출발지 표시
                    dfs(i, j, i, j, 0);
                    visited[i][j] = false;
                    set.remove(map[i][j]);  // 복귀
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int cx, int cy, int sx, int sy, int ch) {

        for (int d = ch; d < 4; d++) {
            int nx = cx + dir[d][0];
            int ny = cy + dir[d][1];

//            for (boolean[] booleans : visited) {
//                System.out.println(Arrays.toString(booleans));
//            }
//            System.out.println(set);
//            System.out.println();

            if (set.size() >= 3 && nx == sx && ny == sy) {
                // System.out.println(set + "check");
                cnt = set.size();
                answer = Math.max(answer, cnt);
                return;
            }

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {  // 범위, 방문 검사
                continue;
            }

            if (set.contains(map[nx][ny])) {  // 중복 검사
                continue;
            }

            visited[nx][ny] = true;
            set.add(map[nx][ny]);
            dfs(nx, ny, sx, sy, d);
            set.remove(map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}