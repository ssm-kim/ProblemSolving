import java.io.*;
import java.util.*;

public class Main {

    static final int WALLCNT = 3;

    static int safeyCnt = 0;
    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] originMap;

    // 벽을 세울 위치 ( 최대 3개 )
    static ArrayList<int[]> emptyCells = new ArrayList<>();
    static ArrayList<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        originMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
                if (originMap[i][j] == 0) {
                    emptyCells.add(new int[] {i, j});
                }
                else if (originMap[i][j] == 2) {
                    virus.add(new int[] {i, j});
                }
            }
        }

        // 벽을 세울 수 있는 모든 경우의 조합
        combinations(0, 0);

        System.out.println(safeyCnt);
    }

    static void spreadVirus() {
        // 초기 바이러스 위치 복사
        Queue<int[]> queue = new LinkedList<>();
        for (int[] pos : virus) {
            queue.offer(pos);
        }
        // 원본 맵 복사
        int[][] cloneMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            cloneMap[i] = originMap[i].clone();
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 빈 칸이면 바이러스를 퍼뜨린다.
                if (cloneMap[nx][ny] == 0) {
                    cloneMap[nx][ny] = 2;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        // 안전 영역 최댓값 갱신
        int curCnt = 0;
        for (int[] row : cloneMap) {
            for (int col : row) {
                if (col == 0) curCnt++;
            }
        }
        safeyCnt = Math.max(safeyCnt, curCnt);
    }

    static void combinations(int depth, int start) {
        if (depth == WALLCNT) {
            spreadVirus();  // 벽 세운 후, 바이러스를 퍼뜨림.
            return;
        }

        for (int i = start; i < emptyCells.size(); i++) {
            int x = emptyCells.get(i)[0];
            int y = emptyCells.get(i)[1];

            originMap[x][y] = 1;  // 현재 좌표에 벽을 세운다.
            combinations(depth + 1, i + 1);
            originMap[x][y] = 0;  // 백트래킹
        }
    }
}