import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static final int N = 5;
    static int answer = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static boolean[][] visited;
    static char[][] board = new char[N][N];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[] seq = new int[7];
        combinations(0, 0, seq);  // 25개 위치 중 7개 선택하는 모든 조합 생성

        System.out.println(answer);
    }

    static void combinations(int depth, int start, int[] seq) {
        if (depth == 7) {
            // 7개 위치가 모두 연결되어 있는지 체크
            if (isConnected(seq)) {
                // 선택된 7개 중 'S'가 4개 이상인지 체크
                int sCnt = 0;
                for (int i : seq) {
                    int cx = i / 5;
                    int cy = i % 5;
                    if (board[cx][cy] == 'S') sCnt++;
                }
                if (sCnt >= 4) answer++;
            }
            return;
        }

        // 25개 위치(0~24)에서 7개 선택하는 조합 생성
        for (int i = start; i < 25; i++) {
            seq[depth] = i;
            combinations(depth + 1, i + 1, seq);
        }
    }

    static boolean isConnected(int[] seq) {
        // 선택된 7개 위치를 grid에 표시
        boolean[][] grid = new boolean[N][N];
        for (int i : seq) {
            grid[i / 5][i % 5] = true;
        }

        // 첫 번째 위치부터 BFS로 연결성 확인
        int sx = seq[0] / N;
        int sy = seq[0] % N;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});

        visited = new boolean[N][N];
        visited[sx][sy] = true;

        int connectCnt = 1;  // 연결된 위치 개수
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            // 상하좌우 인접한 위치 확인
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 선택된 위치이면서 아직 방문하지 않았으면 연결
                if (!visited[nx][ny] && grid[nx][ny]) {
                    visited[nx][ny] = true;
                    connectCnt++;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        return connectCnt == 7;  // 7개가 모두 연결되었는지 확인
    }
}