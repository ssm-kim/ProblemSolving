import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] memoization;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 가로 크기
        M = Integer.parseInt(st.nextToken());  // 세로 크기
        map = new int[N][M];
        memoization = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memoization[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int cx, int cy) {
        if (cx == N - 1 && cy == M - 1) {
            return 1;
        }  // 도착점에 도달하면 1을 반환

        if (memoization[cx][cy] != -1) {
            return memoization[cx][cy];
        }  // 이미 방문했다면 저장된 값을 반환

        memoization[cx][cy] = 0;  // 현재 지점에서 시작하는 경로의 수 1로 초기화

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (map[cx][cy] > map[nx][ny]) {
                memoization[cx][cy] += dfs(nx, ny);
            }  // 다음 지점에서 가능한 경로의 수를 더함
        }

        for (int[] ints : memoization) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();


        return memoization[cx][cy];
    }
}