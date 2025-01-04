import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int N;
    static int[][] map = new int[101][101];  // 초기값 2차원 배열 (100x100 크기)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    map[j][k] = 1;
                }
            }  // 10x10 크기의 색종이 영역을 1로 채움
        }

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                int cx = i;
                int cy = j;

                if (map[cx][cy] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nx = cx + dx[k];
                        int ny = cy + dy[k];

                        if (nx < 0 || nx > 100 || ny < 0 || ny > 100) {
                            continue;
                        }  // 범위 검사

                        if (map[nx][ny] == 0) {
                            answer++;
                        }  // 인접한 칸이 비어있으면 둘레에 포함
                    }
                }  // 색종이가 있는 칸인 경우

            }
        }  // 각 칸마다 상하좌우를 확인하여 둘레 계산
        System.out.println(answer);
    }
}