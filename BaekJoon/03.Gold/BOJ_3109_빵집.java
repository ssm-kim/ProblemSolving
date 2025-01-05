import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, answer = 0;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) answer++;
        }

        System.out.println(answer);
    }

    static boolean dfs(int cx, int cy) {
        if (cy == C - 1) {
            return true;
        }  // 마지막 열에 도달했다면 파이프라인 연결 성공

        // 현재 위치 방문 ( 이 경로는 다른 파이프 사용 X )
        visited[cx][cy] = true;
        for (int i = 0; i < 3; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 맵을 벗어나거나, 이미 방문했거나, 건물이 있는 곳이면 pass
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 'x') continue;

            if (dfs(nx, ny)) {
                visited[nx][ny] = true;
                return true;
            }  // 연결 성공 시 즉시 반환
        }
        return false;  // 모든 방향 연결 실패
    }
}