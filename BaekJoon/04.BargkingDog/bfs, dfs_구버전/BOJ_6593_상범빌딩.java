import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y, z, time;

    public Node(int x, int y, int z, int time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }
}

public class Main {

    static int h, r, c;
    static int[] dx = {0, 0, -1, 1, 0, 0};   // 상하좌우 + 층간이동 없음
    static int[] dy = {1, -1, 0, 0, 0, 0};   // 좌우 + 층간이동 없음
    static int[] dz = {0, 0, 0, 0, 1, -1};   // 층간이동 (위층/아래층)

    static char[][][] building;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());  // 높이
            r = Integer.parseInt(st.nextToken());  // 행
            c = Integer.parseInt(st.nextToken());  // 열

            if (h == 0 && r == 0 && c == 0) break; // 종료 조건

            int sx = 0, sy = 0, sz = 0;
            building = new char[h][r][c];

            // 3차원 빌딩 입력 받으면서 시작점 찾기
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < r; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < c; k++) {
                        building[i][j][k] = s.charAt(k);
                        if (building[i][j][k] == 'S') { // 시작점 S 발견
                            sx = j;  // 행
                            sy = k;  // 열
                            sz = i;  // 높이
                        }
                    }
                }
                br.readLine();  // 층 구분 빈 줄 처리
            }

            visited = new boolean[h][r][c];
            int time = bfs(sx, sy, sz);

            System.out.println(time == -1 ? "Trapped!" : "Escaped in " + time + " minute(s).");
        }
    }

    static int bfs(int sx, int sy, int sz) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, sz, 0));
        visited[sz][sx][sy] = true; // 시작점 방문 체크

        while (!queue.isEmpty()) {
            Node nd = queue.poll();

            // 출구 E 도달
            if (building[nd.z][nd.x][nd.y] == 'E') {
                return nd.time;
            }

            // 6방향 이동 (상하좌우 + 위아래층)
            for (int i = 0; i < 6; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int nz = nd.z + dz[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || nz < 0 || nz >= h || visited[nz][nx][ny]) continue;

                // 벽(#)이 아니면 이동 가능
                if (building[nz][nx][ny] != '#') {
                    visited[nz][nx][ny] = true;
                    queue.offer(new Node(nx, ny, nz, nd.time + 1));
                }
            }
        }
        return -1; // 탈출 불가능
    }
}