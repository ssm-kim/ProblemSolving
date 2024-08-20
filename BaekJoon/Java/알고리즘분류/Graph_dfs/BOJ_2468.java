import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static Direction[] dir = {
            new Direction(0, 1), new Direction(1, 0),
            new Direction(0, -1), new Direction(-1, 0)
    };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int answer = 0;
        for (int height = 0; height <= maxHeight; height++) {
            int safeAreas = 0;
            visited = new boolean[n][n];  // 방문 체크

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 현재 지점이 n보다 크고 방문 하지 않은 영역
                    if (map[i][j] > height && !visited[i][j]) {
                        dfs(new int[]{i, j}, height);
                        safeAreas++;
                    }
                }
            }
            answer = Math.max(answer, safeAreas);
        }
        System.out.println(answer);
    }

    static void dfs(int[] pos, int height) {
        /**
         * 기저 조건  3가지
         * 1. 범위 검사
         * 2. 방문 확인
         * 3. 물이 잠겼는지 검사 (n 이하)
         */
        int cx = pos[0];
        int cy = pos[1];

        if (cx < 0 || cx >= n || cy < 0 || cy >= n || visited[cx][cy] || map[cx][cy] <= height) {
            return;
        }

        visited[cx][cy] = true;  // 방문 확인

        for (int i = 0; i < 4; i++) {
            int nx = cx + dir[i].dx;
            int ny = cy + dir[i].dy;
            dfs(new int[]{nx, ny}, height);
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
