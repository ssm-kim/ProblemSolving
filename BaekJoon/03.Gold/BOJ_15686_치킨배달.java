import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {

    int x;
    int y;
    int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class Main {

    static int minDistance = Integer.MAX_VALUE;
    static int n, m;
    static int homeCnt;  // 총 집의 갯수
    static int[][] board;

    static int[] select;
    static ArrayList<int[]> chickenPos = new ArrayList<>();  // 치킨 집 좌표

    static Queue<Point> queue = new LinkedList<>();
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());  // 치킨 집을 최대 M개 선택

        board = new int[n][n];                 // 도시의 정보
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    homeCnt++;
                }
                if (board[i][j] == 2) {
                    chickenPos.add(new int[]{i, j});  // 치킨 집 좌표 저장
                }
            }
        }

        visited = new boolean[n][n];  // 방문 배열 초기화
        select = new int[m];          // 전체 치킨집에서 m개의 치킨집을 선택하는 조합
        combinations(0, 0);
        System.out.println(minDistance);
    }

    static void combinations(int depth, int start) {
        if (depth == m) {
            // 매번 탐색을 위한 큐와 방문 배열 초기화
            queue.clear();
            for (boolean[] visit : visited) Arrays.fill(visit, false);

            for (int sIdx : select) {
                int[] pos = chickenPos.get(sIdx);
                queue.offer(new Point(pos[0], pos[1], 0));
                visited[pos[0]][pos[1]] = true;  // 시작점은 방문 체크 하기
            }

            int distanceSum = bfs();  // 최단 거리 탐색
            minDistance = Math.min(minDistance, distanceSum);
            return;
        }

        for (int i = start; i < chickenPos.size(); i++) {
            select[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }

    static int bfs() {
        int curHomeCnt = 0;
        int distanceSum = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int cx = p.x;
            int cy = p.y;
            int distance = p.distance;

            if (board[cx][cy] == 1) {
                distanceSum += distance;
                curHomeCnt++;
            }  // 현재 칸이 집이라면

            if (curHomeCnt == homeCnt) break;  // 모든 집을 다 방문 했다면

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 + 방문 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny, distance + 1));
            }
        }
        return distanceSum;
    }
}