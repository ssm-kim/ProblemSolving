import java.io.*;
import java.util.*;

public class Main {

    static final int ALPHABET_SIZE = 26;

    static int R, C, answer;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[] check = new boolean[ALPHABET_SIZE];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        check[map[0][0] - 'A'] = true;
        answer = 1;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int cx, int cy, int cnt) {
        // 현재까지 방문한 알파벳 개수와 answer 비교하여 최댓값 갱신
        answer = Math.max(answer, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }  // 범위 검사

            // 현재 위치의 알파벳을 인덱스로 변환 (A=0, B=1, ...)
            int idx = map[nx][ny] - 'A';

            if (!check[idx]) {
                check[idx] = true;          // 알파벳 방문 체크
                dfs(nx, ny, cnt + 1);  // 다음 위치로 이동, 카운트 증가
                check[idx] = false;         // 백트래킹: 알파벳 방문 체크 해제
            }
        }
    }
}
