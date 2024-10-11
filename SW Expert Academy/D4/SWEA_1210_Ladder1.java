import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static final int ROW = 100, COL = 100;
    static int[][] map;
    static ArrayList<Integer> start;
    static int[] dx = {0, 0, 1};
    static int[] dy = {1, -1, 0};
    static boolean[][] visited = new boolean[ROW][COL];
    static boolean target;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            tc = Integer.parseInt(br.readLine());

            start = new ArrayList<>();
            map = new int[ROW][COL];
            for (int i = 0; i < ROW; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < COL; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                if (map[0][i] == 1) {
                    start.add(i);
                } // 출발 지점 리스트
            }

            target = false;
            for (int col : start) {
                visited[0][col] = true;
                dfs(0, col);
                if (target) {
                    System.out.println("#" + tc + " " + col);
                    break;
                }
            }
        }
    }

    static void dfs(int cx, int cy) {

        if (cx == 99 && map[cx][cy] == 2) {
            target = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= ROW || ny < 0 || ny >= COL || visited[nx][ny]) {
                continue;
            } // 범위 검사

            if (map[nx][ny] != 0) {
                visited[nx][ny] = true;
                dfs(nx, ny);
                visited[nx][ny] = false;
                return;
            }
        }
    }
}