import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static Point p;
    static int n, m;
    static int[] dice = new int[6];  // 주사위 6면: [북 아래 남 위 서 동]
    static int[] commands;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 세로 크기
        m = Integer.parseInt(st.nextToken());  // 가로 크기
        board = new int[n][m];

        // 주사위 놓을 곳의 좌표
        p = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 명령의 갯수
        int k = Integer.parseInt(st.nextToken());
        commands = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        for (int command : commands) {
            if (command == 1) {  // 동쪽
                p.y += 1;               // 인덱스 이동
                if (check()) {          // 범위 체크
                    rotateEast();       // 주사위 이동 먼저
                    downDiceProcess();  // 바닥면 처리
                    System.out.println(dice[3]);
                } else {
                    p.y -= 1;
                }
            }
            else if (command == 2) {  // 서쪽
                p.y -= 1;
                if (check()) {  // 범위 체크
                    rotateWest();
                    downDiceProcess();
                    System.out.println(dice[3]);
                }  else {
                    p.y += 1;
                }
            }
            else if (command == 3) {  // 북쪽
                p.x -= 1;
                if (check()) {  // 범위 체크
                    rotateNorth();
                    downDiceProcess();
                    System.out.println(dice[3]);
                } else {
                    p.x += 1;
                }
            }
            else if (command == 4) {  // 남쪽
                p.x += 1;
                if (check()) {  // 범위 체크
                    rotateSouth();
                    downDiceProcess();
                    System.out.println(dice[3]);
                } else {
                    p.x -= 1;
                }
            }
        }
    }

    static void downDiceProcess() {  // 바닥면 처리
        if (board[p.x][p.y] == 0) {
            board[p.x][p.y] = dice[1];
        }  // 이동한 칸에 쓰여 있는 수가 0이면 주사위 바닥면에 쓰여 있는 수가 칸에 복사된다.
        else {
            dice[1] = board[p.x][p.y];
            board[p.x][p.y] = 0;
        }  // 0이 아닌 경우 칸에 쓰여 있는 수가 주사위 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
    }

    static void rotateSouth() {  // 주사위 6면: [북 아래 남 위]
        int tmp0 = dice[0];  // 북쪽
        int tmp1 = dice[1];  // 아래
        int tmp2 = dice[2];  // 남쪽
        int tmp3 = dice[3];  // 위쪽
        dice[0] = tmp1;
        dice[1] = tmp2;
        dice[2] = tmp3;
        dice[3] = tmp0;
    }

    static void rotateNorth() {  // 주사위 6면: [북 아래 남 위]
        int tmp0 = dice[0];  // 북쪽
        int tmp1 = dice[1];  // 아래
        int tmp2 = dice[2];  // 남쪽
        int tmp3 = dice[3];  // 위쪽
        dice[0] = tmp3;
        dice[1] = tmp0;
        dice[2] = tmp1;
        dice[3] = tmp2;
    }

    static void rotateEast() {  // 주사위 6면: [북 아래 남 위 서 동]
        int tmp5 = dice[5];  // 동쪽
        int tmp3 = dice[3];  // 위
        int tmp4 = dice[4];  // 서쪽
        int tmp1 = dice[1];  // 아래
        dice[5] = tmp3;
        dice[3] = tmp4;
        dice[4] = tmp1;
        dice[1] = tmp5;
    }

    static void rotateWest() {  // 주사위 6면: [북 아래 남 위 서 동]
        int tmp5 = dice[5];  // 동쪽
        int tmp3 = dice[3];  // 위
        int tmp4 = dice[4];  // 서쪽
        int tmp1 = dice[1];  // 아래
        dice[5] = tmp1;
        dice[3] = tmp5;
        dice[4] = tmp3;
        dice[1] = tmp4;
    }

    static boolean check () {
        return p.x >= 0 && p.x < n && p.y >= 0 && p.y < m;
    }
}