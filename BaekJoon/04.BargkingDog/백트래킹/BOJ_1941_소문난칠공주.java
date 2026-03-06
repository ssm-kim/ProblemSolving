import java.io.*;
import java.util.*;

public class Main {

    static int n = 5, selected = 7;
    static int answer = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] seq;
    static boolean[][] visited;
    static char[][] board = new char[n][n];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            char[] students = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = students[j];
            }
        }

        visited = new boolean[n][n];
        seq = new int[selected];
        dfs(0, 0);
        System.out.println(answer);
    }

    // 25칸 중 7개 조합 생성
    static void dfs(int depth, int start) {
        if (depth == 7) {
            // 선택된 7칸을 visited에 표시
            for (boolean[] row : visited) Arrays.fill(row, false);

            int sx = -1, sy = -1;
            for (int i : seq) {
                int row = i / 5, col = i % 5;

                if (sx == -1) sx = row; sy = col;
                visited[row][col] = true;
            }

            // BFS 연결 체크 + S 개수 검증
            if (isConnect(sx, sy)) {
                int sCnt = 0;
                for (int i : seq) {
                    int row = i / 5, col = i % 5;
                    if (board[row][col] == 'S') sCnt++;
                }
                if (sCnt >= 4) answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            seq[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    // BFS: 시작점에서 연결된 칸 수가 7이면 전부 연결됨
    static boolean isConnect(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        visited[sx][sy] = false;  // 방문 처리 (true→false로 뒤집어서 체크)

        int connectCnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                if (visited[nx][ny]) {
                    connectCnt++;
                    visited[nx][ny] = false;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        return connectCnt == 7;
    }
}