import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 0, 1, 1};  // 우상단, 우, 우하단, 하단 4방향
    static int[] dy = {1, 1, 1, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[19][19];
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = 0, c = 0, answer = 0;
        loop: for (int j = 0; j < 19; j++) {
            for (int i = 0; i < 19; i++) {
                if (map[i][j] == 1) {  // 검은 바둑 돌
                    boolean state = check(i, j, 1);  // 현재 좌표 + 바둑돌
                    if (state) {
                        answer = 1;
                        r = i + 1;
                        c = j + 1;
                        break loop;
                    }
                }
                if (map[i][j] == 2) {  // 흰 바둑 돌
                    boolean state = check(i, j, 2);  // 현재 좌표 + 바둑돌
                    if (state) {
                        answer = 2;
                        r = i + 1;
                        c = j + 1;
                        break loop;
                    }
                }
            }
        }
        System.out.println(answer == 0 ? answer : answer + "\n" + r + " " + c);
    }

    static boolean check(int i, int j, int color) {
        boolean state = false;
        for (int k = 0; k < 4; k++) {  // 다음 좌표 + 어떤 방향으로 탐색할지 구분 필요
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (0 <= nx && nx < 19 && 0 <= ny && ny < 19 && map[nx][ny] == color) {
                state = search(i, j, k);  // true 면 5개짜리 오목을 찾은 것
                if (state) break;
            }
        }
        return state;
    }

    static boolean search(int sx, int sy, int dir) {  // 시작 좌표, 탐색할 방향
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});

        int color = map[sx][sy];  // 1인지 2인지 판별
        int cnt = 1;              // 시작은 1로 고정 바둑돌 갯수 세기
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int nx = pos[0] + dx[dir];
            int ny = pos[1] + dy[dir];

            if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19) continue;  // 범위 검사
            if (cnt >= 6) return false;  // 6개 이상 놓여져 있다면

            if (map[nx][ny] == color) {
                cnt++;
                queue.offer(new int[]{nx, ny});
            }
        }

        int bx = sx - dx[dir], by = sy - dy[dir];  // 시작칸 바로 뒤에 6목 확인
        if (0 <= bx && bx < 19 && 0 <= by && by < 19) {
            if (map[bx][by] == color) {
                return false;
            }
        }
        return cnt == 5;
    }
}