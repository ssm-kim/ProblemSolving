import java.util.*;
import java.io.*;

class Point {
    int x, y, dir;

    public Point(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
            ", dir=" + dir +
            '}';
    }
}

public class Main {

    static int n, m;
    static int[][] board;
    static Point start;
    // 0 1 2 3 (북 동 남 서)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        start = new Point(sx, sy, dir);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (board[start.x][start.y] == 0) {
                board[start.x][start.y] = 2;  // 청소 완료
            }

            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!checkBlank()) {
                boolean canStop = back();
                if (canStop) break;
            }
            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            else {
                rotate();
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void rotate() {
        // 반시계 방향으로 90도 회전.
        for (int i = 1; i <= 4; i++) {
            // 0 1 2 3 (북 동 남 서)
            int dir = (4 - i) % 4;
            int mvDir = (start.dir + dir) % 4;  // 회전 성공

            int fx = start.x + dx[mvDir];
            int fy = start.y + dy[mvDir];

            // 청소 X 빈칸이면 전진 후 1번으로 돌아간다.
            if (board[fx][fy] == 0) {
                start = new Point(fx, fy, mvDir);
                return;
            }
        }
    }

    static boolean back() {
        // 바라보는 방향 유지 + 한칸 후진 가능O + 한 칸 후진 후 1번으로 간다.
        // 만약, 바라보는 방향 뒤쪽이 벽이면 작동 멈춤
        int curDir = (start.dir + 2) % 4;

        int bx = start.x + dx[curDir];
        int by = start.y + dy[curDir];

        // 뒤쪽 벽이면 멈춤
        if (board[bx][by] == 1) return true;

        start = new Point(bx, by, start.dir);

        return false;
    }

    static boolean checkBlank() {
        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == 1) continue;

            if (board[nx][ny] == 0) {
                return true;
            }  // 주위에 청소되지 않은 빈칸이 하나라도 있다면
        }
        return false;
    }
}