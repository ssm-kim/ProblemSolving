import java.io.*;
import java.util.*;

public class Main {

    static int m, n;
    static int[][] field;
    static int[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());  // 가로 길이
            n = Integer.parseInt(st.nextToken());  // 세로 길이
            field = new int[m][n];    // 배추밭 크기
            visited = new int[m][n];  // 방문 확인
            int minBug = 0;
            int k = Integer.parseInt(st.nextToken());  // 배추가 심어진 갯 수

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());  // 배추 위치 X
                int y = Integer.parseInt(st.nextToken());  // 배추 위치 y
                field[x][y] = 1;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 배추가 심어져 있고 방문하지 않은 구역
                    if (field[i][j] == 1 && visited[i][j] == 0) {
                        // System.out.println(dfs(i, j, 1));  // dfs 탐색
                        dfs(i, j, 1);
                        minBug++;   // 배추 흰 지렁이 추가
                        // break loop;
                    }
                }
            }
            System.out.println(minBug);
        }
    }

    static int dfs(int cx, int cy, int cnt) {
        visited[cx][cy] = 1;  // 현재 구역 방문

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 검사
            if (0 <= nx && nx < m &&  0 <= ny && ny < n) {
                if (field[nx][ny] == 1 && visited[nx][ny] == 0) {
                    int cur = dfs(nx, ny, cnt); // 몇 개의 배추로 된 영역인지 검사 (생략 가능)
                    cnt += cur;
                }
            }
        }
        return cnt;
    }
}