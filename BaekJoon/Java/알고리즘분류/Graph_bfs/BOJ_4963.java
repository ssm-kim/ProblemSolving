import java.io.*;
import java.util.*;

public class Main {

    static int w, h, islandCnt;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());  // 너비
            h = Integer.parseInt(st.nextToken());  // 높이
            islandCnt = 0;  // 섬의 갯수 초기화

            if (w == 0 && h == 0) break;  // 0, 0면 종료

            map = new int[h][w];          // 2차원 배열 초기화
            visited = new boolean[h][w];  // 방문 여부 확인

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                st = new StringTokenizer(s);
                if (s.length() == 1) {    // 1개짜리 섬일 때
                    map[h-1][w-1] = Integer.parseInt(st.nextToken());
                } else {
                    for (int j = 0; j < w; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {  // 섬이면서 방문을 안 했으면
                        bfs(i, j);
                        islandCnt++;
                    }
                }
            }
            System.out.println(islandCnt);
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>(List.of(new int[]{x, y}));

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];  // 현재 좌표
            int cy = pos[1];
            visited[cx][cy] = true;

            for (int i = 0; i < 8; i++) {  // 8방향 탐색
                int nx = cx + dx[i];       // 다음 좌표
                int ny = cy + dy[i];
                // 범위 안에 있고 방문 안했고 섬이면 다음 영역 탐색
                if (0 <= nx && nx < h && 0 <= ny && ny < w
                        && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

