import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] rooms;
    static int[] dx = {-1, 0, 1, 0};  // 북동남서
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 행 + 패딩 제거 (가장 자리 벽)
        m = Integer.parseInt(st.nextToken());  // 열 + 패딩 제거

        rooms = new int[n][m];  // 격자판 크기
        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());    // 로봇청소기 시작 좌표 (r, c) + 패딩 제거
        int c = Integer.parseInt(st.nextToken());
        int startDir = Integer.parseInt(st.nextToken());  // 로봇청소기가 바라보는 방향

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs(r, c, startDir);
//        for (int[] room : rooms) {
//            System.out.println(Arrays.toString(room));
//        }
        System.out.println(ans);
    }

    static int bfs(int r, int c, int startDir) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, startDir});  // x, y, 방향 좌표 + 자체 방문 체크
        int clearCnt = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int cDir = pos[2];  // 바라보는 방향

            if (rooms[cx][cy] == 0) {  // 청소를 한 칸은 2로 표시
                rooms[cx][cy] = 2;
                clearCnt++;
            }

            boolean clean = false;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[(cDir + 3 - i) % 4];
                int ny = cy + dy[(cDir + 3 - i) % 4];

                // 범위를 벗어나는지?
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (rooms[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, (cDir + 3 - i) % 4});
                    clean = true;
                    break;  // 청소되지 않았다면 노드 추가 후 한 칸 전진해야하므로 break;
                }
            }

            if (!clean) {   // 4칸 중 청소되지 않은 빈 칸이 없는 경우
                int backDir = (cDir + 2) % 4;
                int bx = cx + dx[backDir];
                int by = cy + dy[backDir];

                if (0 <= bx && bx < n && 0 <= by && by < m && rooms[bx][by] != 1){
                    queue.offer(new int []{bx,by, cDir});
                }
            }
        }
        return clearCnt;
    }
}