import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;
    int cnt;
    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {

    static int minDist = Integer.MAX_VALUE;
    static int N, M, homeTotalCnt = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> chickenHouse = new ArrayList<>();
    static Queue<Node> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());  // 최대 치킨집 선택 횟수
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) homeTotalCnt++;                      // 전체 집 갯수
                if (map[i][j] == 2) chickenHouse.add(new int[] {i, j});  // 치킨집 좌표 저장
            }
        }

        combinations(0, 0, new ArrayList<int[]>());
        System.out.println(minDist);
    }

    static void combinations(int depth, int start, ArrayList<int[]> selected) {
        if (depth == M) {
            int curDist = bfs(selected);  // BFS 치킨 거리 계산
            minDist = Math.min(minDist, curDist);
            return;
        }  // M개의 치킨집을 선택하는 조합

        for (int i = start; i < chickenHouse.size(); i++) {
            selected.add(chickenHouse.get(i));
            combinations(depth + 1, i + 1, selected);
            selected.remove(selected.size() - 1);  // 백트래킹
        }
    }

    static int bfs(ArrayList<int[]> selected) {

        // queue 및 방문 배열 초기화
        queue.clear();
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], false);

        for (int[] curCoordinate : selected) {
            int sx = curCoordinate[0];
            int sy = curCoordinate[1];
            queue.offer(new Node(sx, sy, 0));
            visited[sx][sy] = true;
        }  // 선택된 치킨집들을 시작점으로 동시에 BFS 시작

        int totalDistance = 0, curHomeCnt = 0;

        while (!queue.isEmpty()) {
            Node nd = queue.poll();
            int cx = nd.x;
            int cy = nd.y;
            int distance = nd.cnt;

            if (map[cx][cy] == 1) {
                totalDistance += distance;  // 집 방문시 누적 거리 추가
                curHomeCnt++;               // 집 방문시 +1
            }

            if (homeTotalCnt == curHomeCnt) {
                break;
            }  // 모든 집을 찾으면 조기 종료

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 검사 + 방문 검사
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, distance + 1));
            }
        }
        return totalDistance;
    }
}