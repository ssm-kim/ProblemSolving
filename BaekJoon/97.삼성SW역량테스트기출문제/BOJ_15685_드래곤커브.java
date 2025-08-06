import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int N = 101, M = 101;

    static int[][] map = new int[N][M];  // 전체 맵 크기

    // 동 북 서 남 (시계 방향)
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 모든 드래곤 커브 그리기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            makeCuv(x, y, dir, k);
        }

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (map[i][j] == 1 && map[i + 1][j] == 1 &&
                    map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void makeCuv(int sx, int sy, int sDir, int k) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(sDir);  // 시작 방향 저장

        // k세대까지 방향 패턴 만들기
        for (int i = 0; i < k; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        // 시작점 표시
        map[sy][sx] = 1;

        // 방향대로 이동하면서 점 찍기
        for (int dir : directions) {
            sy += dx[dir];
            sx += dy[dir];
            map[sy][sx] = 1;
        }
    }
}