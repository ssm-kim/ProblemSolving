import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 버전 1 -> 직접 풀이
public class Main {

    static int n, m, maxValue;
    static int[][] map;

    // 동 남 서 북
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxValue = 0;
                dfs(1, i, j, map[i][j]);

                // ㅗㅜㅏㅓ 모양 따로 처리
                specialShapeCheck(i, j);

                answer = Math.max(answer, maxValue);
            }
        }
        System.out.println(answer);
    }

    static void specialShapeCheck(int cx, int cy) {
        for (int i = 0; i < 4; i++) {
            boolean pass = true;
            int sum = map[cx][cy];
            for (int j = 0; j < 3; j++) {
                // 동 남 서 북
                int nx = cx + dx[(i + j) % 4];
                int ny = cy + dy[(i + j) % 4];

                // 범위만 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    pass = false;
                    break;
                }

                sum += map[nx][ny];
            }
            if (pass) {
                maxValue = Math.max(sum, maxValue);
            }
        }
    }

    static void dfs(int depth, int cx, int cy, int curSum) {
        if (depth == 4) {
            maxValue = Math.max(maxValue, curSum);
            return;
        }

        visited[cx][cy] = true;
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 체크 + 방문 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            dfs(depth + 1, nx, ny, curSum + map[nx][ny]);
        }
        visited[cx][cy] = false;
    }
}

// 버전 2 -> 참고
//public class Main {
//
//    static int n, m, answer;
//    static int[][] map;
//
//    // 동 남 서 북
//    static int[] dx = {0, 1, 0, -1};
//    static int[] dy = {1, 0, -1, 0};
//    static boolean[][] visited;
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        map = new int[n][m];
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < m; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        visited = new boolean[n][m];
//        answer = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                visited[i][j] = true;
//                dfs(1, i, j, map[i][j]);
//                visited[i][j] = false;
//            }
//        }
//        System.out.println(answer);
//    }
//
//    static void dfs(int depth, int cx, int cy, int curSum) {
//        if (depth == 4) {
//            answer = Math.max(answer, curSum);
//            return;
//        }
//
//        for (int i = 0; i < 4; i++) {
//            int nx = cx + dx[i];
//            int ny = cy + dy[i];
//
//            // 범위 체크 + 방문 체크
//            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
//
//            visited[nx][ny] = true;
//            if (depth == 2) {
//                dfs(depth + 1, cx, cy, curSum + map[nx][ny]);
//            }
//
//            dfs(depth + 1, nx, ny, curSum + map[nx][ny]);
//            visited[nx][ny] = false;
//        }
//    }
//}