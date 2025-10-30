import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] board;
    static ArrayList<int[]> firePath;  // 현재 불의 위치들
    static ArrayList<int[]> move;      // 현재 상근이의 가능한 위치들

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());  // 열
            n = Integer.parseInt(st.nextToken());  // 행
            board = new char[n][m];

            firePath = new ArrayList<>();
            move = new ArrayList<>();

            // 초기 상태 설정: 상근이와 불의 시작 위치 찾기
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = s.charAt(j);
                    if (board[i][j] == '@') {
                        move.add(new int[] {i, j, 1});
                    }
                    else if (board[i][j] == '*') {
                        firePath.add(new int[] {i, j});
                    }
                }
            }

            int time = 0;
            while (true) {
                time++;

                // 1. 탈출 가능한지 먼저 체크 (불이 확산되기 전에)
                int pass = isEscape();
                if (pass != -1) break;

                // 2. 더 이상 갈 곳이 없으면 실패
                if (move.isEmpty()) {
                    time = -1;
                    break;
                }

                // 3. 불 먼저 확산
                fireSpread();

                // 4. 상근이 이동
                move();
            }
            System.out.println(time == -1 ? "IMPOSSIBLE" : time);
        }
    }

    // 현재 위치에서 한 칸만 더 가면 탈출 가능한지 체크
    static int isEscape() {
        for (int[] path : move) {
            int sx = path[0];
            int sy = path[1];
            int seconds = path[2];

            for (int i = 0; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];

                // 경계 밖으로 나가면 탈출 성공
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    return seconds;
                }
            }
        }
        return -1;
    }

    // 상근이가 갈 수 있는 모든 곳으로 이동
    static void move() {
        ArrayList<int[]> nextMove = new ArrayList<>();
        for (int[] path : move) {
            int sx = path[0];
            int sy = path[1];
            int seconds = path[2];

            for (int i = 0; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];

                // 범위 체크 + 이미 방문한 곳 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == '@') continue;

                // 빈 칸으로만 이동 가능
                if (board[nx][ny] == '.') {
                    board[nx][ny] = '@';  // 방문 표시
                    nextMove.add(new int[] {nx, ny, seconds + 1});
                }
            }
        }

        move.clear();
        move = nextMove;
    }

    // 불이 갈 수 있는 모든 곳으로 확산
    static void fireSpread() {
        ArrayList<int[]> nextSpread = new ArrayList<>();
        for (int[] path : firePath) {
            int cx = path[0];
            int cy = path[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 벽이나 이미 불이 있는 곳은 확산 불가
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == '#' || board[nx][ny] == '*') continue;

                board[nx][ny] = '*';  // 불 확산 + 방문 표시
                nextSpread.add(new int[] {nx, ny});
            }
        }

        firePath.clear();
        firePath = nextSpread;
    }
}