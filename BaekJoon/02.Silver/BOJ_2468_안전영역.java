import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, maxSafeArea;
    static int[][] area;
    static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        area = new int[n][n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                area[i][j] = sc.nextInt();
                maxHeight = Math.max(area[i][j], maxHeight);  // 최대 높이
            }
        }

        for (int height = 0; height <= maxHeight; height++) {
            int safeArea = 0;
            visited = new boolean[n][n];  // 방문 체크

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (area[i][j] > height && !visited[i][j]) {  // 방문 영역이 물에 잠기지 않고 방문 X
                        bfs(i, j, height, visited);
                        safeArea++;
                    }
                }
            }
            maxSafeArea = Math.max(safeArea, maxSafeArea);  // 최대 안전 영역 갱신
        }
        System.out.println(maxSafeArea);
    }

    static void bfs(int x, int y, int height, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            visited[cx][cy] = true;  // 현재 방문 처리

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {         // 범위 확인 여부
                    if (area[nx][ny] > height && !visited[nx][ny]) {  // 다음 방문 값이 height 보다 크고 방문 처리 X
                        queue.offer(new int[]{nx, ny});  // next (x, y) 좌표 삽입
                        visited[nx][ny] = true;          // 다음 방문 처리
                    }
                }
            }
        }
    }
}

// dfs
/*
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = 0;
    static int N, limitHeight;
    static Set<Integer> heights = new HashSet<>();
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                heights.add(map[i][j]);
            }
        }

        for (Object height : heights.toArray()) {
            limitHeight = (int) height;
            visited = new boolean[N][N];
            int cnt = 0;  // 현재 높이에서의 안전 영역 개수

            // 모든 지점을 시작점으로 고려
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > limitHeight) {
                        cnt++;  // 새로운 안전 영역 발견
                        dfs(i, j);
                    }  // 방문하지 않았고, 현재 침수 높이보다 높은 지점에서 DFS 시작
                }
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer == 0 ? 1 : answer);
    }

    static void dfs(int cx, int cy) {
        visited[cx][cy] = true;  // 현재 위치 방문 처리

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

            if (map[nx][ny] > limitHeight) {
                dfs(nx, ny);
            }  // 다음 위치가 침수 높이보다 높으면 이동
        }
    }
}
 */
