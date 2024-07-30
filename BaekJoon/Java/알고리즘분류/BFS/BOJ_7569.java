package algoClassification.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

    static int[][][] arr;  // 행, 열, 높이
    static int m, n, h;    // 행, 열, 높이
    static int[] dx = {0, 0, 1, -1, 0, 0};  // 행
    static int[] dy = {1, -1, 0, 0, 0, 0};  // 열
    static int[] dz = {0, 0, 0, 0, 1, -1};  // 높이
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());  // 행
        n = Integer.parseInt(st.nextToken());  // 열
        h = Integer.parseInt(st.nextToken());  // 높이
        arr = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k});  // 익은 토마토(1) 좌표  ->  큐에 삽입
                    }
                }
            }
        }

        System.out.println(bfs()); // 너비 우선 탐색

    }

    static int bfs() {
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cz = pos[0];
            int cx = pos[1];
            int cy = pos[2];

            for (int i = 0; i < 6; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nz = cz + dz[i];

                // 범위, 다음 노드가 '0'이면 큐에 삽입
                if ((0 <= nx && nx < n && 0 <= ny && ny < m && 0 <= nz && nz < h ) && arr[nz][nx][ny] == 0) {
                    queue.offer(new int[]{nz, nx, ny});     // 방문 노드 좌표 추가
                    arr[nz][nx][ny] = arr[cz][cx][cy] + 1;  // 다음 노드가 '0'  ->  현재 노드 +1 (자체 방문 체크)
                }
            }
        }

        int maxDays = 0;  // 최대 일수 출력
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (arr[i][j][k] == 0) return -1;
                    maxDays = Math.max(maxDays, arr[i][j][k]);
                }
            }
        }
        return maxDays-1;
    }
}