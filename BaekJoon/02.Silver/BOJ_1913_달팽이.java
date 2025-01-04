import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int search = Integer.parseInt(br.readLine());
        int[] answer = new int[2];

        int[][] map = new int[N][N];
        int cx = 0;
        int cy = 0;
        int dir = 0;

        // 바깥쪽에서 안쪽으로
        for (int num = N * N; num > 0; num--) {
            map[cx][cy] = num;
            if (search == map[cx][cy]) {
                answer[0] = cx + 1;
                answer[1] = cy + 1;
            }  // 탐색할 좌표값

            cx += dx[dir];
            cy += dy[dir];

            if (0 <= cx && cx < N && 0 <= cy && cy < N && map[cx][cy] == 0) {
                continue;
            }
            else {
                // 복귀
                cx -= dx[dir];
                cy -= dy[dir];

                dir++;
                if (dir == 4) {
                    dir %= 4;
                }

                // 방향 인덱스 증가 후 이동
                cx += dx[dir];
                cy += dy[dir];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : map) {
            for (int val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}