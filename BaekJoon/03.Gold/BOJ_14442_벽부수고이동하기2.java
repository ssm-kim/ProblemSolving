import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    int distance;
    int destroyCnt; // 현재까지 부순 벽의 개수
    public Point(int x, int y, int distance, int destroyCnt) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.destroyCnt = destroyCnt;
    }
}

public class Main {

    static int N, M, K;
    static int[][] map;
    // 핵심: 3차원 visited 배열 - [벽을 부순 개수][x][y]
    // 같은 좌표라도 벽을 부순 개수에 따라 다른 상태로 취급
    static boolean[][][] visited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  // 최대 K개까지 벽을 부술 수 있음
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }

        // visited[벽을 부순 개수][x][y] - 0개부터 K개까지
        visited = new boolean[K + 1][N][M];
        int answer = bfs(0, 0);

        System.out.println(answer);
    }

    static int bfs(int sx, int sy) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx, sy, 1, 0));  // 시작점, 거리 1, 벽 부순 개수 0
        visited[0][sx][sy] = true;  // 벽을 0개 부순 상태에서 시작점 방문

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 목적지에 도달하면 현재 거리 반환 (최단 거리 보장됨)
            if (p.x == N - 1 && p.y == M - 1) {
                return p.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위 체크 + 현재 벽 부순 개수 상태에서 이미 방문한 곳인지 체크
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[p.destroyCnt][nx][ny]) continue;


                // 빈 공간(0)으로 이동하는 경우
                if (map[nx][ny] == 0 && !visited[p.destroyCnt][nx][ny]) {
                    visited[p.destroyCnt][nx][ny] = true;  // 같은 벽 부순 개수 상태로 방문 처리
                    queue.offer(new Point(nx, ny, p.distance + 1, p.destroyCnt));  // 벽 부순 개수 유지
                }
                // 벽(1)을 만난 경우
                else if (map[nx][ny] == 1) {
                    // 핵심: 아직 K개 미만으로 벽을 부쉈다면 벽을 부수고 이동 가능
                    if (p.destroyCnt < K && !visited[p.destroyCnt + 1][nx][ny]) {
                        visited[p.destroyCnt + 1][nx][ny] = true;  // 벽을 하나 더 부순 상태로 방문 처리
                        queue.offer(new Point(nx, ny, p.distance + 1, p.destroyCnt + 1));  // 벽 부순 개수 증가
                    }
                }
            }
        }
        return -1;  // 도달하지 못하면 -1
    }
}