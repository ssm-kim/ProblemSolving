import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] board;
    static ArrayList<int[]> fire = new ArrayList<>();
    static ArrayList<int[]> move = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'J') move.add(new int[] {i, j});
                if (board[i][j] == 'F') fire.add(new int[] {i, j});
            }
        }

        int time = 0;
        while (true) {
            time++;

            spread();  // 불 확산

            boolean isEscape = moveJihoon();  // 지훈 이동
            if (isEscape) {
                System.out.println(time);
                break;
            }  // 탈출 성공
            if (move.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                break;
            }  // 탈출 실패
        }
    }

    static void spread() {
        ArrayList<int[]> newFire = new ArrayList<>();
        for (int[] f : fire) {
            int cx = f[0];
            int cy = f[1];
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || board[nx][ny] == '#' || board[nx][ny] == 'F') continue;

                board[nx][ny] = 'F';
                newFire.add(new int[] {nx, ny});
            }
        }
        fire = newFire;  // 다음 위치 갱신
    }

    static boolean moveJihoon() {
        ArrayList<int[]> newMove = new ArrayList<>();
        for (int[] mv : move) {
            int cx = mv[0];
            int cy = mv[1];
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) return true;  // 탈출 성공

                if (board[nx][ny] == '.') {
                    board[nx][ny] = 'J';
                    newMove.add(new int[] {nx, ny});
                }
            }
        }
        move = newMove;  // 다음 위치 갱신
        return false;
    }
}