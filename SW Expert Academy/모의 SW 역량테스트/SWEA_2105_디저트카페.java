import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, answer;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] desertKinds;
    static int[] dx = {1,  1, -1, -1};
    static int[] dy = {1, -1, -1,  1};

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
                    desertKinds = new boolean[101];  // 총 디저트 종류 ( 1 ~ 100 )
                    visited[i][j] = true;
                    desertKinds[map[i][j]] = true;
                    dfs(1, i, j, i, j, 0);
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int cafeVisitCnt, int cx, int cy, int bx, int by, int previousDir) {
        // previousDir : 경로가 한 방향으로만 진행되도록 이전 방향과 같거나 다음 방향으로만 이동 가능
        for (int i = previousDir; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx == bx && ny == by && cafeVisitCnt >= 4) {
                answer = Math.max(answer, cafeVisitCnt);
                return;
            }  // 시작점으로 돌아옴. 사각형 완성 시 정답 갱신 (카페 4개 이상 방문)

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;           // 이미 방문 했으면
            if (desertKinds[map[nx][ny]]) continue;  // 동일한 디저트라면

            // 방문 처리 및 디저트 종류 체크
            visited[nx][ny] = true;
            desertKinds[map[nx][ny]] = true;

            // 다음 지점 이동 (현재 방향 i를 이전 방향으로 전달)
            dfs(cafeVisitCnt + 1, nx, ny, bx, by, i);

            // 백트래킹: 방문 및 디저트 체크 취소
            desertKinds[map[nx][ny]] = false;
            visited[nx][ny] = false;
        }
    }
}