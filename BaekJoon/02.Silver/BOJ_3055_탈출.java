import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer;
    static char[][] map;
    static Queue<int[]> start, waters;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        map = new char[R][C];
        start = new ArrayDeque<>();
        waters = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    start.offer(new int[] { i, j, 0 });
                } // 시작 위치
                else if (map[i][j] == '*') {
                    waters.offer(new int[] { i, j });
                } // 물일 때 좌표
            }
        }
        bfs();
        System.out.println(answer == 0 ? "KAKTUS" : answer);
    }

    private static void bfs() {
        while (!start.isEmpty()) {

            int check1 = waters.size(); // 퍼뜨려야할 갯수
            while (check1 > 0) {
                int[] pos = waters.poll();
                int cx = pos[0];
                int cy = pos[1];

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        continue;
                    } // 범위 검사

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        waters.offer(new int[] { nx, ny });
                    } // 물 이동
                }
                check1--;
            }

            int check2 = start.size();
            while (check2 != 0) {
                int[] pos = start.poll();
                int cx = pos[0];
                int cy = pos[1];
                int distance = pos[2];

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        continue;
                    } // 범위 검사

                    if (map[nx][ny] == 'D') {
                        answer = Math.max(distance + 1, answer);
                        return;
                    } // 목적지 도착

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'S';
                        start.offer(new int[] { nx, ny, distance + 1 });
                    } // 고슴도치 이동
                }
                check2--;
            }
        }
    }
}