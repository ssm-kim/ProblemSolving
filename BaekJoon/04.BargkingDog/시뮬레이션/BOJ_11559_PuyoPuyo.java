import java.io.*;
import java.util.*;

public class Main {

    static int n = 12, m = 6;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] board = new char[n][m];

    static ArrayList<int[]> startLst = new ArrayList<>();
    static ArrayList<int[]> destroyLst = new ArrayList<>();

    static boolean[][] visited = new boolean[n][m];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] != '.') startLst.add(new int[] {i, j});
            }
        }

        ArrayList<int[]> tagetLst = new ArrayList<>();
        int cascadeCnt = 0;
        while (true) {

            // 초기화
            tagetLst.clear();
            for (boolean[] visit : visited) Arrays.fill(visit, false);

            // 폭파할 수 있는 뿌요가 있는지 확인
            for (int[] cur : startLst) {
                int sx = cur[0];
                int sy = cur[1];

                if (!visited[sx][sy]) {
                    destroyLst.clear();

                    int neighborhoodCnt = dfs(sx, sy, sx, sy);
                    if (neighborhoodCnt >= 4) {
                        // 폭파할 뿌요 좌표들
                        tagetLst.addAll(destroyLst);
                    }
                }
            }

            // 폭파할 Puyo 없으면 종료
            if (tagetLst.isEmpty()) break;
            isDestroy(tagetLst);  // 한번에 여러 곳을 동시에 폭파
            cascadeCnt++;

            // 중력 작용
            downPuyo();

            // Puyo 출발 지점 초기화
            searchStartPuyo();
        }
        System.out.println(cascadeCnt);
    }

    static void searchStartPuyo() {
        startLst.clear();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.') startLst.add(new int[] {i, j});
            }
        }
    }

    static void downPuyo() {
        // 좌하단부터  좌상단까지 탐색해서 위에서부터 하나씩 끌어 내린다
        for (int col = 0; col < m; col++) {
            for (int row = n - 1; row >= 0; row--) {
                if (board[row][col] == '.') {
                    int tmpRow = row;  // 임시 행 인덱스 활용
                    while (true) {
                        // 범위 체크
                        if (--tmpRow < 0) break;

                        char target = board[tmpRow][col];
                        if (target != '.') {
                            board[row][col] = target;
                            board[tmpRow][col] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static void isDestroy(ArrayList<int[]> tagetLst) {
        for (int[] path : tagetLst) board[path[0]][path[1]] = '.';
    }

    static int dfs(int cx, int cy, int sx, int sy) {
        int cnt = 1;
        visited[cx][cy] = true;
        destroyLst.add(new int[] {cx, cy});

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (board[nx][ny] == '.' || visited[nx][ny]) continue;

            // 같은 뿌요 일때만 dfs 추가
            if (board[sx][sy] == board[nx][ny]) {
                cnt += dfs(nx, ny, sx, sy);
            }
        }
        return cnt;
    }
}