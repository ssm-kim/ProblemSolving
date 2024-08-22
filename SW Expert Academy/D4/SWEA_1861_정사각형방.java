import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }  // 입력 받기

            visited = new boolean[n][n];  // 방문 체크 배열
            int startRoom = Integer.MAX_VALUE;  // 처음 출발 방 번호
            int maxRoomCnt = 0; // 최대 방 이동 갯수

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;   // 방문 체크
                        int roomCnt = dfs(i, j);
                        visited[i][j] = false;  // 복귀

                        if (roomCnt > maxRoomCnt)  {
                            maxRoomCnt = roomCnt;
                            startRoom = map[i][j];
                        } else if (roomCnt == maxRoomCnt) {  // 방 번호 갱신
                            startRoom = Math.min(map[i][j], startRoom);
                        }
                    }
                }
            }

            // 출력
            System.out.println("#" + tc + " " + startRoom + " " + maxRoomCnt);
        }
    }

    static int dfs(int cx, int cy) {
        int diff, count = 1;

        for (int i = 0; i < 4; i++) {  // 4방향 탐색
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 검사 + 방문 여부 검사
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                continue;
            }

            // 정확히 1이 더 크다면
            diff = map[nx][ny] - map[cx][cy];
            if (diff == 1) {
                visited[nx][ny] = true;  // 방문 체크
                count += dfs(nx, ny);
                visited[nx][ny] = false; // 복귀
            }
        }
        return count;
    }
}

