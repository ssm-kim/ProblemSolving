import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 다른 방법 풀이: 맨해튼 거리 버전  ->  시간복잡도 + 메모리 효율

public class Main {

    static int n, m, answer;
    static int[][] map;
    static int[][] selectedChickens;  // 선택된 치킨집 좌표들
    static ArrayList<int[]> chickenList = new ArrayList<>();  // 모든 치킨집 좌표
    static ArrayList<int[]> houseList = new ArrayList<>();    // 모든 집 좌표

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) houseList.add(new int[] {i, j});      // 집 좌표 저장
                if (map[i][j] == 2) chickenList.add(new int[] {i, j});    // 치킨집 좌표 저장
            }
        }

        answer = Integer.MAX_VALUE;
        selectedChickens = new int[m][2];  // m개의 치킨집을 선택할 배열

        // 치킨집 조합 생성 시작
        combinations(0, 0);

        System.out.println(answer);
    }

    /**
     * 맨해튼 거리를 직접 계산하여 도시의 치킨 거리 구하기
     * 각 집마다 선택된 치킨집들 중 가장 가까운 거리를 찾아 합산
     */
    static int calculateCityChickenDistance() {
        int totalDistance = 0;

        // 모든 집에 대해 치킨 거리 계산
        for (int[] house : houseList) {
            int houseX = house[0];
            int houseY = house[1];
            int minChickenDistance = Integer.MAX_VALUE;

            // 선택된 치킨집들 중 가장 가까운 거리 찾기
            for (int[] chicken : selectedChickens) {
                int chickenX = chicken[0];
                int chickenY = chicken[1];

                // 맨해튼 거리 계산: |x1-x2| + |y1-y2|
                int distance = Math.abs(houseX - chickenX) + Math.abs(houseY - chickenY);
                minChickenDistance = Math.min(minChickenDistance, distance);
            }

            totalDistance += minChickenDistance;
        }

        return totalDistance;

    }
    static void combinations(int depth, int start) {
        if (depth == m) {  // m개의 치킨집을 모두 선택했다면
            int distance = calculateCityChickenDistance();
            answer = Math.min(answer, distance);
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selectedChickens[depth][0] = chickenList.get(i)[0];
            selectedChickens[depth][1] = chickenList.get(i)[1];
            combinations(depth + 1, i + 1);  // 백트래킹
        }
    }
}


// 직접 푼 풀이: BFS 기반 버전
//class Point {
//    int x, y, distance;
//
//    public Point(int x, int y, int distance) {
//        this.x = x;
//        this.y = y;
//        this.distance = distance;
//    }
//}
//
//public class Main {
//
//    static int n, m, answer, houseCount;
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {1, -1, 0, 0};
//    static int[][] map;
//    static int[][] selectedChickens;  // 선택된 치킨집 좌표들
//    static ArrayList<int[]> chickenList = new ArrayList<>();  // 모든 치킨집 좌표
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        map = new int[n][n];
//        houseCount = 0;
//
//        // 도시 정보 입력 및 집, 치킨집 개수 파악
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < n; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if (map[i][j] == 1) houseCount++;  // 집 개수 카운트
//                if (map[i][j] == 2) chickenList.add(new int[] {i, j});  // 치킨집 좌표 저장
//            }
//        }
//
//        answer = Integer.MAX_VALUE;
//        selectedChickens = new int[m][2];  // m개의 치킨집 선택
//        combinations(0, 0);
//
//        System.out.println(answer);
//    }
//
//    // 선택된 치킨집으로부터 모든 집까지의 최단거리 합을 구함.
//    static int searchDistance() {
//        Queue<Point> queue = new LinkedList<>();
//        boolean[][] visited = new boolean[n][n];
//
//        // 선택된 모든 치킨집을 시작점으로 큐에 추가
//        for (int[] chickenPos : selectedChickens) {
//            int sx = chickenPos[0];
//            int sy = chickenPos[1];
//            queue.offer(new Point(sx, sy, 0));
//            visited[sx][sy] = true;
//        }
//
//        int totalDistance = 0;
//        int foundHouses = 0;  // 찾은 집의 개수
//
//        while (!queue.isEmpty()) {
//            Point c = queue.poll();
//
//            // 집 발견 시, 거리 누적
//            if (map[c.x][c.y] == 1) {
//                totalDistance += c.distance;
//                foundHouses++;
//            }
//
//            if (foundHouses == houseCount) break;  // 모든 집을 찾으면 탐색 종료
//
//            for (int i = 0; i < 4; i++) {
//                int nx = c.x + dx[i];
//                int ny = c.y + dy[i];
//
//                // 범위 체크 + 방문 체크
//                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
//
//                visited[nx][ny] = true;
//                queue.offer(new Point(nx, ny, c.distance + 1));
//            }
//        }
//        return totalDistance;
//    }
//
//    static void combinations(int depth, int start) {
//        if (depth == m) {  // m개의 치킨집 선택
//            int distance = searchDistance();
//            answer = Math.min(answer, distance);
//            return;
//        }
//
//        for (int i = start; i < chickenList.size(); i++) {
//            selectedChickens[depth][0] = chickenList.get(i)[0];
//            selectedChickens[depth][1] = chickenList.get(i)[1];
//            combinations(depth + 1, i + 1);  // 백트래킹
//        }
//    }
//}