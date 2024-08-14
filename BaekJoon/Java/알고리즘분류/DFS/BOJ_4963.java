import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    static int w, h;
    static int[][] map;
    static boolean[][] visited;
    static Direction[] dir = {  new Direction(0, 1), new Direction(1, 0),
                                new Direction(0, -1), new Direction(-1, 0),
                                new Direction(1, 1), new Direction(-1, 1),
                                new Direction(-1, -1), new Direction(1, -1)  };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        while (true) {
            w = sc.nextInt();  // 너비
            h = sc.nextInt();  // 높이
            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = 0;
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    static void dfs(int start_x, int start_y) {

        // 8방향 탐색
        for (int i = 0; i < 8; i++) {
            int next_x = start_x + dir[i].dx;
            int next_y = start_y + dir[i].dy;

            // 범위 체크
            if (next_x < 0 || next_x >= h || next_y < 0 || next_y >= w) {
                continue;
            }

            // 방문 여부 + 땅, 바다 여부
            if (!visited[next_x][next_y] && map[next_x][next_y] == 1) {
                visited[next_x][next_y] = true;
                dfs(next_x, next_y);
            }
        }
    }
}

class Direction {
    int dx;
    int dy;

    // 파라미터를 받는 생성자
    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}