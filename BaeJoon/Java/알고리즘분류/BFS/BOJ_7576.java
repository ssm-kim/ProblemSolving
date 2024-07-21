package algoClassification.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

    static int m, n, answer;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());  // 열
        n = Integer.parseInt(st.nextToken());  // 행
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    queue.offer(new int[]{i,j});  // 익은 토마토(1) 좌표  ->  큐에 삽입
                }
            }
        }

        bfs();  // 너비 우선 탐색

        boolean state = true;
        loop: for (int i = 0; i < n; i++) {
                if (!state) {
                    answer = -1;
                    break;
                }
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 0) {  // 익지 않은 토마토가 있다면 loop를 통해 2중 for문을 빠져나온다.
                        state = false;
                        continue loop;
                    }
                    answer = Math.max(answer, arr[i][j]);  // 최대값 갱신
                }
        }

        if (answer == -1)  // 토마토가 모두 익지 못하는 상황
            System.out.println(answer);
        else
            System.out.println(answer - 1);  // bfs 첫 시작이 항상 '1'이므로 arr 에서 값을 구한 후 -1을 해준다.
    }

    static void bfs() {

        while (!queue.isEmpty()) {  // 큐가 비어 있지 않다면
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위, 다음 노드가 '0'이면 큐에 삽입
                if ((0 <= nx && nx < n && 0 <= ny && ny < m) && arr[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});  // 방문 노드 좌표 추가
                    arr[nx][ny] = arr[cx][cy] + 1;   // 다음 노드가 '0'  ->  현재 노드 +1 (자체 방문 체크)
                }
            }
        }
    }
}
