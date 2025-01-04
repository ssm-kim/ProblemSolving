package algoClassification.BFS;

import java.io.*;
import java.util.*;

// https://yummy0102.tistory.com/297  >  참조

public class BOJ_2178 {

    static int[][] arr;
    static int n, m;
    static boolean[][] visit;
    // 4방향 탐색
    static int[] dx = {0, 1, 0, -1};  
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];        // 입력값 2차원 배열
        visit = new boolean[n][m];  // 방문 확인 2차원 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            List<String> row = Arrays.asList(st.nextToken().split(""));
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(row.get(j));
            }
        }

        // 최단거리  >  bfs 활용
        visit[0][0] = true;  // 출발지는 방문 확인
        bfs(0, 0);
        System.out.println(arr[n-1][m-1]);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {  // 큐가 비어있지않다면
            int[] curPos = queue.poll();  // 현재 좌표 위치
            int cx = curPos[0];
            int cy = curPos[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];  // 4방향 좌표
                int ny = cy + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {  // 범위안에 포함되고
                    if (!visit[nx][ny] && arr[nx][ny] == 1) {  // 방문하지 않고 길이 맞다면 방문 체크
                        visit[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});  // 다음 방문할 좌표 큐에 삽입
                        arr[nx][ny] = arr[cx][cy] + 1;   // 현재 좌표 +1
                    }
                }
            }
        }
    }
}

