import java.io.*;
import java.util.*;

class point {
    int x;
    int y;
    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    int homeCnt = dfs(new point(i, j));
                    answer.add(homeCnt);
                }
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i : answer) {
            System.out.println(i);
        }
    }

    static int dfs(point cur) {
        int count = 1;
        map[cur.x][cur.y] = 0;  // 방문 체크

        for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }  // 범위 검사

            if (map[nx][ny] == 1) {
                count += dfs(new point(nx, ny));  // 집이 있으니 +1
            }  // 집이 있는 곳만 탐색
        }
        return count;
    }
}

