import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, answer;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] desertKinds;  // 디저트 종류
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            answer = -1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[N][N];
                    desertKinds = new boolean[101];  // 총 1 ~ 100가지  (0번 인덱스 사용안 함)
                    visited[i][j] = true;            // 시작 지점 방문 체크
                    desertKinds[map[i][j]] = true;   // 시작 지점 디저트 체크
                    dfs(1, i, j, i, j, 0);
                }
            }

            System.out.println("#" +tc + " " + answer);
        }
    }

    private static void dfs(int cnt, int cx, int cy, int ix, int iy, int preNum) {
        // 현재 위치에서 가능한 방향 탐색 (이전 방향보다 같거나 큰 방향만 가능)
        for (int i = preNum; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx == ix && ny == iy && cnt >= 4) {
                answer = Math.max(answer, cnt);
                return;
            }  // 시작점으로 돌아왔고, 4개 이상의 카페를 방문했다면 최대 카페 방문 수 갱신

            // 범위를 벗어나거나, 이미 방문했거나, 같은 디저트면 스킵
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;
            if (desertKinds[map[nx][ny]]) continue;

            // 방문 체크 및 디저트 체크
            visited[nx][ny] = true;
            desertKinds[map[nx][ny]] = true;

            // 다음 카페 방문
            dfs(cnt + 1, nx, ny, ix, iy, i);

            // 백트래킹
            desertKinds[map[nx][ny]] = false;
            visited[nx][ny] = false;
        }
    }
}