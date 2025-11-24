import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    static int n, answer;
    static int[][] board;
    static boolean[][] visited;
    static HashSet<Integer> set = new HashSet<>();
    // 우하, 좌하, 좌상, 우상
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 양 옆과 밑에 값이 있어야 사각형 만들 수 있다.
            answer = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 가능 여부 체크
                    if (!check(i, j)) continue;

                    visited = new boolean[n][n];
                    set.clear();  // 중복 체크
                    set.add(board[i][j]);
                    visited[i][j] = true;
                    dfs(i, j, i, j, 0);  // 방향 인덱스 추가
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int cx, int cy, int sx, int sy, int dir) {
        // 다음 방향으로 이동 (같은 방향 또는 다음 방향)
        for (int nextDir = dir; nextDir < 4; nextDir++) {
            int nx = cx + dx[nextDir];
            int ny = cy + dy[nextDir];

            // 시작점으로 돌아왔는지 체크 (최소 4개 디저트)
            if (nx == sx && ny == sy && set.size() >= 4) {
                answer = Math.max(set.size(), answer);
                return;
            }

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            // 방문 체크, 중복 디저트 체크
            if (visited[nx][ny] || set.contains(board[nx][ny])) continue;

            visited[nx][ny] = true;
            set.add(board[nx][ny]);
            dfs(nx, ny, sx, sy, nextDir);
            set.remove(board[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static boolean check(int sx, int sy) {
        // 서쪽 체크
        if (sy - 1 < 0) return false;

        // 동쪽 체크
        if (sy + 1 >= n) return false;

        // 남쪽 2칸 이상 체크
        if (sx + 2 >= n) return false;

        return true;
    }
}