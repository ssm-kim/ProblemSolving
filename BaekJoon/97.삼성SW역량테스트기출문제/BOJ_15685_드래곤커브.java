import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int N = 101, M = 101;
    static int[][] map = new int[N][M];

    // 동 북 서 남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            makeCuv(x, y, d, g);
        }

        // 1x1 정사각형 개수 세기
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (map[i][j] == 1 &&
                    map[i + 1][j] == 1 &&
                    map[i][j + 1] == 1 &&
                    map[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void makeCuv(int cx, int cy, int dir, int g) {
        ArrayList<Integer> directions = new ArrayList<>();  // 좌표 저장
        directions.add(dir);  // 0세대: 초기 방향

        // 각 세대마다 이전 방향들을 역순으로 시계방향 회전해서 추가
        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {  // 역순
                int curDir = directions.get(j);
                int nextDir = (curDir + 1) % 4;  // 시계 방향 90도 회전
                directions.add(nextDir);
            }
        }

        map[cy][cx] = 1;  // 시작점 표시

        // 방향 배열대로 이동하며 점 찍기
        for (int d : directions) {
            cy += dx[d];
            cx += dy[d];
            map[cy][cx] = 1;
        }
    }
}