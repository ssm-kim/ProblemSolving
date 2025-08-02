import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static int sx, sy, dir;

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 주사위 전개도 : 윗면, 아랫면, 북, 동, 남, 서
    static int[] dice = {1, 6, 2, 3, 5, 4};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());  // 이동하는 횟수
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dir = 1;  // 초기 방향: 동쪽
        sx = 0; sy = 0;  // 시작 좌표: (0,0)
        int score = 0;
        for (int i = 0; i < k; i++) {
            // 1. 주사위 굴리기 (범위 벗어나면 방향 반대로)
            int[] coordinate = rollDice();
            sx = coordinate[0];
            sy = coordinate[1];

            // 2. 점수 계산 (연결된 같은 숫자 칸 개수)
            int moveCnt = bfs();
            int arrivePlace = map[sx][sy];
            score += (arrivePlace * moveCnt);


            // 3. 다음 방향 결정 (아랫면 vs 지도값)
            if (dice[1] > arrivePlace) {
                dir = (dir + 1) % 4;  // 시계 방향 회전
            } else if (dice[1] < arrivePlace) {
                dir = (dir - 1 + 4) % 4;  // 반시계 방향 회전
            }
        }
        System.out.println(score);
    }

    // 연결된 같은 숫자 칸의 개수 계산
    static int bfs() {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        visited[sx][sy] = true;
        int count = 1;  // 현재 칸 체크

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[sx][sy] != map[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny});
                count++;
            }
        }
        return count;
    }

    // 주사위 굴리기
    static int[] rollDice() {
        int nx = sx + dx[dir];
        int ny = sy + dy[dir];

        // 범위 벗어나면 방향을 반대로 변경
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            switch (dir) {
                case 0:
                    nx = sx + dx[2]; ny = sy + dy[2]; dir = 2;
                    break;
                case 1:
                    nx = sx + dx[3]; ny = sy + dy[3]; dir = 3;
                    break;
                case 2:
                    nx = sx + dx[0]; ny = sy + dy[0]; dir = 0;
                    break;
                case 3:
                    nx = sx + dx[1]; ny = sy + dy[1]; dir = 1;
                    break;
            }
        }

        // 굴러간 방향에 따른 주사위 면 변환
        int north = dice[2], south = dice[4], up = dice[0], down = dice[1];
        int east = dice[3], west = dice[5];

        // 주사위 전개도 인덱스 : 윗면, 아랫면, 북, 동, 남, 서
        switch (dir) {
            case 0:  // 북쪽으로 굴리기
                dice[0] = south; dice[1] = north;
                dice[2] = up;    dice[4] = down;
                break;
            case 1:  // 동쪽으로 굴리기
                dice[0] = west; dice[1] = east;
                dice[3] = up;   dice[5] = down;
                break;
            case 2:  // 남쪽으로 굴리기
                dice[0] = north; dice[1] = south;
                dice[2] = down;  dice[4] = up;
                break;
            case 3:  // 서쪽으로 굴리기
                dice[0] = east; dice[1] = west;
                dice[3] = down; dice[5] = up;
                break;
        }
        return new int[] {nx, ny};
    }
}
