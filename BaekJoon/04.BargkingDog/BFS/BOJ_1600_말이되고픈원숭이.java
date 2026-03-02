import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x, y, jumpCnt, distance;

    public Point(int x, int y, int jumpCnt, int distance) {
        this.x = x;
        this.y = y;
        this.jumpCnt = jumpCnt;
        this.distance = distance;
    }
}

public class Main {

    static int k, w, h, answer;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int[] hdx = {2, 1, -2, -1, -2, -1, 2, 1};
    static int[] hdy = {1, 2, -1, -2, 1, 2, -1, -2};

    static boolean[][][] visited;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 왼쪽 위 출발 -> 오른쪽 아래 이동
        visited = new boolean[h][w][k + 1];  // 3차원 배열
        answer = -1;
        bfs(0, 0);

        System.out.println(answer);
    }

    static void bfs(int sx, int sy) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx, sy, k, 0));
        visited[sx][sy][k] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 목적지 도착 시 최단 거리 반환
            if (p.x == h - 1 && p.y == w - 1) {
                answer = p.distance;
                break;
            }

            // 일반 이동 (상하좌우) - 항상 가능
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (board[nx][ny] == 1 || visited[nx][ny][p.jumpCnt]) continue;

                visited[nx][ny][p.jumpCnt] = true;
                queue.offer(new Point(nx, ny, p.jumpCnt, p.distance + 1));
            }

            // 말 이동 (나이트 이동) - 점프 횟수 남았을 때만
            if (p.jumpCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = p.x + hdx[i];
                    int ny = p.y + hdy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    // 점프 사용 시 jumpCnt - 1 상태로 방문 체크
                    if (board[nx][ny] == 1 || visited[nx][ny][p.jumpCnt - 1]) continue;

                    visited[nx][ny][p.jumpCnt - 1] = true;
                    queue.offer(new Point(nx, ny, p.jumpCnt - 1, p.distance + 1));
                }
            }
        }
    }
}