import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 행
        M = Integer.parseInt(st.nextToken());  // 열
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rotateCnt = Math.min(N, M) / 2;  // 테두리 갯수

        for (int r = 0; r < R; r++) {
            for (int i = 0; i < rotateCnt; i++) {
                int temp = map[i][i];

                // 위쪽: 왼쪽으로 밀기
                for (int j = i; j < M - 1 - i; j++) {
                    map[i][j] = map[i][j + 1];
                }

                // 오른쪽: 위로 밀기
                for (int j = i; j < N - 1 - i; j++) {
                    map[j][M - 1 - i] = map[j + 1][M - 1 - i];
                }

                // 아래쪽: 오른쪽으로 밀기
                for (int j = M - 1 - i; j > i; j--) {
                    map[N - 1 - i][j] = map[N - 1 - i][j - 1];
                }


                // 왼쪽: 아래로 밀기
                for (int j = N - 1 - i; j > i; j--) {
                    map[j][i] = map[j - 1][i];
                }
                map[i + 1][i] = temp;
            }  // 전체 테두리 갯수
        }  // 전체 회전 횟수

        StringBuilder sb = new StringBuilder();
        for (int[] row : map) {
            for (int col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}