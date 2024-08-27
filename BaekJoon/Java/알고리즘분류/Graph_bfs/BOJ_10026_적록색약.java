import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static char[][] map;
    static boolean[][][] visited;  // 3차원 방문 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = c[j];
            }
        }

        int isNormal = 0, isNotNormal = 0;
        visited = new boolean[2][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[0][i][j]) {  // 정상 ( 적록 색약 X )
                    isNormal++;
                    bfs(true, i, j);
                }
                if (!visited[1][i][j]) {  // 비정상 ( 적록 색약 O )
                    isNotNormal++;
                    bfs(false, i, j);
                }
            }
        }
        System.out.println(isNormal + " " + isNotNormal);
    }

    static void bfs(boolean state, int cx, int cy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{cx, cy});
        // 시작점 방문
        if (state) visited[0][cx][cy] = true;
        else visited[1][cx][cy] = true;


        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (0 > nx || nx >= n || 0 > ny || ny >= n) {
                    continue;
                }

                if (state && !visited[0][nx][ny]) {    // 정상 + 방문 X
                    if (map[nx][ny] == map[cx][cy]) {  // next 컬러, current 컬러
                        visited[0][nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }

                else if (!state && !visited[1][nx][ny]) {   // 비정상 + 방문 X
                    if ((map[nx][ny] == 'R' || map[nx][ny] == 'G') && (map[cx][cy] == 'R' || map[cx][cy] == 'G')) {  // next 컬러, current 컬러
                        visited[1][nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    } else if (map[nx][ny] == map[cx][cy]) {
                        visited[1][nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}