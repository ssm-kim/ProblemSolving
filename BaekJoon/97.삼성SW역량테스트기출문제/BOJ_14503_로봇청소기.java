import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y, dir;

    public Node (int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {

    static int n, m;
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());    // 로봇 청소기 x 좌표
        int sy = Integer.parseInt(st.nextToken());    // 로봇 청소기 y 좌표
        int sDir = Integer.parseInt(st.nextToken());  // 로봇 청소시가 바라보는 방향

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(sx, sy, sDir));
    }

    static int bfs(int sx, int sy, int sDir) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, sDir));

        int clearCnt = 0;
        while(!queue.isEmpty()) {
            Node nd = queue.poll();

            // 현재 칸이 비어있다면 청소한다.
            if (map[nd.x][nd.y] == 0) {
                map[nd.x][nd.y] = 2;
                clearCnt++;
            }

            // 빈 칸 여부 확인
            boolean hasCleanable = false;
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                // 범위 벗어나거나 벽이면 패스
                if (nx < 0 || nx  >= n || ny < 0 || ny >= m || map[nx][ny] != 0) continue;

                // 청소되지 않은 빈 칸 존재
                hasCleanable = true;
                break;
            }

            // 청소되지 않은 빈 칸이 있는 경우, 반시계 방향 90도 회전하며 앞쪽 칸 청소되지 않으면 한 칸 전진
            if (hasCleanable) {
                // 북 동 남 서
                for (int i = 0; i < 4; i++) {
                    int newDir = (nd.dir - i - 1) % 4;
                    newDir = newDir < 0 ? newDir + 4 : newDir;

                    int nx = nd.x + dx[newDir];
                    int ny = nd.y + dy[newDir];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
                        queue.offer(new Node(nx, ny, newDir));
                        break;
                    }
                }
            }
            // 청소되지 않은 빈 칸이 없는 경우, 방향 유지 + 한 칸 후진
            else {
                switch (nd.dir) {
                    case 0:  // 현재 북쪽을 바라봄. (뒤로 남쪽)
                        if (nd.x + 1 < n && map[nd.x + 1][nd.y] != 1) {
                            queue.offer(new Node(nd.x + 1, nd.y, nd.dir));
                        }
                        break;
                    case 1:  // 현재 동쪽을 바라봄. (뒤로 서쪽)
                        if (nd.y - 1 >= 0 && map[nd.x][nd.y - 1] != 1) {
                            queue.offer(new Node(nd.x, nd.y - 1, nd.dir));
                        }
                        break;
                    case 2:  // 현재 남쪽을 바라봄. (뒤로 북쪽)
                        if (nd.x - 1 >= 0 && map[nd.x - 1][nd.y] != 1) {
                            queue.offer(new Node(nd.x - 1, nd.y, nd.dir));
                        }
                        break;
                    case 3:  // 현재 서쪽을 바라봄. (뒤로 동쪽)
                        if (nd.y + 1 < m && map[nd.x][nd.y + 1] != 1) {
                            queue.offer(new Node(nd.x, nd.y + 1, nd.dir));
                        }
                        break;
                }
            }
        }
        return clearCnt;
    }
}