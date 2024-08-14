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
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    static void dfs(int start_x, int start_y) {
        // 기저 조건 : 범위 체크
        if (start_x < 0 || start_x >= h || start_y < 0 || start_y >= w) {
            return;
        }
        // 기저 조건 : 방문 O 또는 바다
        if (visited[start_x][start_y] || map[start_x][start_y] == 0) {
            return;
        }

        // 현재 노드 방문 처리
        visited[start_x][start_y] = true;

        // 8방향 탐색
        for (Direction d: dir) {
            int next_x = start_x + d.dx;
            int next_y = start_y + d.dy;

            // 다음 위치로 dfs 호출
            dfs(next_x, next_y);
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