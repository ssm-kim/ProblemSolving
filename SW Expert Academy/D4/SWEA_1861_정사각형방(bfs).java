import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int N, roomMV, roomNum;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N][N];
            roomMV = 0;
            roomNum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int curMvRoom = bfs(i, j);

                    if (curMvRoom > roomMV) {
                        roomNum = map[i][j];
                        roomMV = curMvRoom;
                    }  // 최댓값 갱신 (방 번호 + 방 이동 갯수)
                    else if (curMvRoom == roomMV) {
                        roomNum = Math.min(roomNum, map[i][j]);
                    }  // 둘 다 이동할 수 있는 방 갯수가 같다면

                    for (boolean[] row : visited) {
                        Arrays.fill(row, false);
                    }  // 방문 초기화
                }
            }
            System.out.println("#" + tc + " " + roomNum + " " + roomMV);
        }
    }

    static int bfs(int sx, int sy) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        int mvCnt = 1;  // 현재 방 포함
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                    continue;
                }  // 범위 검사 + 방문 검사

                if (map[cx][cy] + 1 == map[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    mvCnt++;
                }  // 이전 방과 다음 방이 1 차이가 나야 한다.
            }
        }
        return mvCnt;
    }
}
