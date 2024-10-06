import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer = 0;
    static int[][] map, copyMap;
    static ArrayList<int[]> virus = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        map = new int[N][M];
        copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 열
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            bfs();
            return;
        }  // 순열 여기서 안전 영역의 최대 크기를 구한다

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }  // map 복사

        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : virus) {
            queue.offer(ints);
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        int safety = 0;
        for (int[] row : copyMap) {
            for (int i : row) {
                if (i == 0) {
                    safety++;
                }
            }
        }

        answer = Math.max(answer, safety);
    }
}