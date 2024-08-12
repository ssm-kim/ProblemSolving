import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static char[][] board;
    static boolean[][][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (char) br.read();
            }
            br.readLine();
        }

//        for (char[] chars : board) {
//            System.out.println(Arrays.toString(chars));
//        }

        visited = new boolean[2][n][n];  // 3차원 배열 활용
        int normalCnt = 0, abnormalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[0][i][j]) {  // 0번 방문 배열 정상인 사람이 보는 구역 수
                    normalCnt += bfs(i, j, 0, true);
                }

                if (!visited[1][i][j]) {  // 1번 방문 배열 비정상인 사람이 보는 구역 수
                    abnormalCnt += bfs(i, j, 1, false);
                }
            }
        }
        System.out.printf("%d\n%d", normalCnt, abnormalCnt);

//        for (boolean[][] booleans : visited) {
//            for (boolean[] aBoolean : booleans) {
//                System.out.println(Arrays.toString(aBoolean));
//            }
//            System.out.println();
//        }
    }

    // space -> 3차원 방문 배열
    static int bfs(int start_x, int start_y, int space, boolean isDivision) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_x, start_y});
        visited[space][start_x][start_y] = true;
        char curColor = board[start_x][start_y];  // 비교 대상

        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 검사
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                // isDivision (정상) 방문하지 않았고 현재 컬러와 같다면
                if (isDivision && !visited[space][nx][ny] && board[nx][ny] == curColor) {
                    queue.offer(new int[]{nx, ny});
                    visited[space][nx][ny] = true;
                }

                // !isDivision (비정상) 방문하지 않았고
                if (!isDivision && !visited[space][nx][ny]) {
                    // 현재 컬러가 빨강과 초록이면서 다음 구역도 빨강과 초록이면 카운트를 한다.
                    if (curColor == 'R' || curColor == 'G') {
                        if (board[nx][ny] == 'R' || board[nx][ny] == 'G') {
                            queue.offer(new int[] {nx, ny});
                            visited[space][nx][ny] = true;
                        }
                    }
                    else if (board[nx][ny] == curColor) {
                        queue.offer(new int[] {nx,ny});
                        visited[space][nx][ny] = true;
                    }
                }
            }
        }
        return 1;
    }
}