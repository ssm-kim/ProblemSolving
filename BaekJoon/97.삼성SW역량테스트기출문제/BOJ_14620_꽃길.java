import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final int SELECT = 3;
    static int n;
    static int minPrice;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static boolean[][] visited;
    static int[][] board;
    static ArrayList<int[]> coordinate = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                coordinate.add(new int[]{i, j});
            }
        }

        minPrice = Integer.MAX_VALUE;
        combinations(0, 0, 0);
        System.out.println(minPrice);
    }

    static void combinations(int depth, int start, int currentCost) {
        // 기저 조건: 3개 꽃을 모두 심었으면 최솟값 갱신
        if (depth == SELECT) {
            minPrice = Math.min(minPrice, currentCost);
            return;
        }

        for (int i = start; i < n * n; i++) {
            int[] pos = coordinate.get(i);
            int cx = pos[0];
            int cy = pos[1];

            // 가지치기: 꽃을 심을 수 있는 위치만 탐색
            if (canPlant(cx, cy)) {
                int cost = plantFlowers(cx, cy);  // 꽃 심기 + 비용 계산

                combinations(depth + 1, i + 1, currentCost + cost);  // 재귀 호출

                recoveryPlant(cx, cy);  // 백트래킹: 상태 복구
            }
        }
    }

    static boolean canPlant(int cx, int cy) {
        // 중앙과 4방향이 모두 비어있는지 체크
        if (visited[cx][cy]) return false;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) return false;
        }
        return true;
    }

    static int plantFlowers(int cx, int cy) {
        // 중앙 + 4방향에 꽃 심고 총 비용 계산
        int cost = board[cx][cy];
        visited[cx][cy] = true;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            visited[nx][ny] = true;
            cost += board[nx][ny];
        }
        return cost;
    }

    static void recoveryPlant(int cx, int cy) {
        // 백트래킹을 위해 중앙 + 4방향 복구
        visited[cx][cy] = false;
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            visited[nx][ny] = false;
        }
    }
}

// 아래는 모든 조합 구한 후 검증하므로 백트래킹 방식보단 메모리, 속도 측면에서 느림.
//
//public class Main {
//
//    static final int SELECT = 3;
//    static int n;
//    static int minPrice;
//
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {1, -1, 0, 0};
//    static int[] positions = new int[SELECT];
//
//    static boolean[][] visited;
//    static int[][] board;
//    static ArrayList<int[]> coordinate = new ArrayList<>();
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        n = Integer.parseInt(br.readLine());
//        board = new int[n][n];
//        visited = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < n; j++) {
//                board[i][j] = Integer.parseInt(st.nextToken());
//                coordinate.add(new int[]{i, j});
//            }
//        }
//
//        // 총 n * n개 중에 3개를 뽑는 조합. 단, 좌표를 구해야 한다.
//        minPrice = Integer.MAX_VALUE;
//        combinations(0, 0);
//        System.out.println(minPrice);
//    }
//
//    static void combinations(int depth, int start) {
//        if (depth == SELECT) {
//
//            int cost = calculate();
//            if (cost != -1) {
//                minPrice = Math.min(minPrice, cost);
//            }
//            return;
//        }
//
//        for (int i = start; i < n * n; i++) {
//            positions[depth] = i;
//            combinations(depth + 1, i + 1);
//        }
//    }
//
//    static int calculate() {
//        // 방문 배열 초기화
//        for (boolean[] visit : visited) Arrays.fill(visit, false);
//        int cost = 0;
//
//        // 꽃을 심을 좌표
//        for (int position : positions) {
//            int[] pos = coordinate.get(position);
//            int cx = pos[0];
//            int cy = pos[1];
//            cost += board[cx][cy];
//            visited[cx][cy] = true;  // 현재 위치 방문
//
//            for (int i = 0; i < 4; i++) {
//                int nx = cx + dx[i];
//                int ny = cy + dy[i];
//
//                // 범위 검사 + 이미 방문되어 있다면 각 꽃끼리 겹침.
//                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) return -1;
//
//                visited[nx][ny] = true;  // 다음 위치 방문
//                cost += board[nx][ny];
//            }
//        }
//        return cost;
//    }
//}