import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> cctvList = new ArrayList<>();
    static int[][][] camDirections = {
            {},
            {{0}, {1}, {2}, {3}},                           // 1번 : 한 방향씩
            {{0, 2}, {1, 3}},                               // 2번 : 서로 반대 방향
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},               // 3번 : ㄱ자 방향
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},   // 4번 : 세 방향
            {{0, 1, 2, 3}}                                  // 5번 : 모든 방향
    };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new int[]{i, j});
                }  // 출발 지점 확인
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] curMap) {
        if (depth == cctvList.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (curMap[i][j] == 0) cnt++;
                }
            }
            answer = Math.min(answer, cnt);
            return;
        }  // 모든 CCTV 확인 완료

        // 현재 CCTV 위치 및 타입
        int[] pos = cctvList.get(depth);
        int type = map[pos[0]][pos[1]];

        // 해당 CCTV의 가능한 모든 방향 조합 시도
        for (int[] dirs : camDirections[type]) {

            int[][] copyMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                copyMap[i] = curMap[i].clone();
            }  // 맵 복사

            // 각 방향으로 표시
            for (int dir : dirs) {
                int nx = pos[0];
                int ny = pos[1];

                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    if (copyMap[nx][ny] == 6) break;

                    // 빈칸이면 감시 영역 표시
                    if (copyMap[nx][ny] == 0) copyMap[nx][ny] = -1;
                }
            }
            // 다음 CCTV 확인
            dfs(depth + 1, copyMap);
        }
    }
}