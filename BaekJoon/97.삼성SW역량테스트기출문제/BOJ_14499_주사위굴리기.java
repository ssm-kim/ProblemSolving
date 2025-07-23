
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    // 북, 남, 서, 동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] commands;
    static int[] dice = new int[6], diceCopy = new int[6];  // 북쪽, 하단, 남쪽, 상단, 서쪽, 동쪽
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int cx = Integer.parseInt(st.nextToken());
        int cy = Integer.parseInt(st.nextToken());
        int commandCnt = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        commands = new int[commandCnt];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < commandCnt; i++) {
            boolean hasMove = true;  // 이동 여부 파악
            commands[i] = Integer.parseInt(st.nextToken());
            switch (commands[i]) {
                case 1: {  // 동쪽 이동
                    int nx = cx + dx[3];
                    int ny = cy + dy[3];

                    if (!rangeCheck(nx, ny)) {
                        hasMove = false;
                        break;
                    }

                    copyDice();
                    mvEast();  // 동쪽으로 주사위 이동
                    diceAndMapChange(nx, ny);

                    cx = nx;  // 주사위 시작 좌표 이동
                    cy = ny;
                    break;
                }
                case 2: {  // 서쪽 이동
                    int nx = cx + dx[2];
                    int ny = cy + dy[2];

                    if (!rangeCheck(nx, ny)) {
                        hasMove = false;
                        break;
                    }

                    copyDice();
                    mvWest();  // 서쪽으로 주사위 이동
                    diceAndMapChange(nx, ny);

                    cx = nx;  // 주사위 시작 좌표 이동
                    cy = ny;
                    break;
                }
                case 3: {  // 북쪽 이동
                    int nx = cx + dx[0];
                    int ny = cy + dy[0];

                    if (!rangeCheck(nx, ny)) {
                        hasMove = false;
                        break;
                    }

                    copyDice();
                    mvNorth();  // 북쪽으로 주사위 이동
                    diceAndMapChange(nx, ny);

                    cx = nx;  // 주사위 시작 좌표 이동
                    cy = ny;
                    break;
                }
                case 4: {  // 남쪽 이동
                    int nx = cx + dx[1];
                    int ny = cy + dy[1];

                    if (!rangeCheck(nx, ny)) {
                        hasMove = false;
                        break;
                    }

                    copyDice();
                    mvSouth();  // 남쪽으로 주사위 이동
                    diceAndMapChange(nx, ny);

                    cx = nx;  // 주사위 시작 좌표 이동
                    cy = ny;
                    break;
                }
            }
            if (hasMove) sb.append(dice[3]).append("\n");
        }
        System.out.println(sb);
    }

    static void diceAndMapChange(int nx, int ny) {  //  주사위, 맵 상태 변경
        if (map[nx][ny] == 0) {  // 이동한 칸에 쓰여 있는 수가 0이면, 주사위 하단에 쓰여 있는 수가 복사.
            map[nx][ny] = dice[1];
        }
        else {  // 0이 아닌 경우, 칸에 쓰여 있는 수가 주사위 하단에 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            dice[1] = map[nx][ny];
            map[nx][ny] = 0;
        }
    }

    static void copyDice() {  // 주사위 원본 복사
        for (int i = 0; i < 6; i++) diceCopy[i] = dice[i];
    }

    static boolean rangeCheck (int nx, int ny) {  // 범위 체크
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    // 동쪽으로 굴리기: 서→하단→동→상단→서 순환
    static void mvEast() {
        dice[1] = diceCopy[5];  // 하단 ← 동쪽
        dice[4] = diceCopy[1];  // 서쪽 ← 하단
        dice[3] = diceCopy[4];  // 상단 ← 서쪽
        dice[5] = diceCopy[3];  // 동쪽 ← 상단
    }

    // 서쪽으로 굴리기: 동→하단→서→상단→동 순환
    static void mvWest() {
        dice[1] = diceCopy[4];  // 하단 ← 서쪽
        dice[4] = diceCopy[3];  // 서쪽 ← 상단
        dice[3] = diceCopy[5];  // 상단 ← 동쪽
        dice[5] = diceCopy[1];  // 동쪽 ← 하단
    }

    // 남쪽으로 굴리기: 북→하단→남→상단→북 순환
    static void mvSouth() {
        dice[1] = diceCopy[0];  // 하단 ← 북쪽
        dice[2] = diceCopy[1];  // 남쪽 ← 하단
        dice[3] = diceCopy[2];  // 상단 ← 남쪽
        dice[0] = diceCopy[3];  // 북쪽 ← 상단
    }

    // 북쪽으로 굴리기: 남→하단→북→상단→남 순환
    static void mvNorth() {
        dice[1] = diceCopy[2];  // 하단 ← 남쪽
        dice[2] = diceCopy[3];  // 남쪽 ← 상단
        dice[3] = diceCopy[0];  // 상단 ← 북쪽
        dice[0] = diceCopy[1];  // 북쪽 ← 하단
    }
}