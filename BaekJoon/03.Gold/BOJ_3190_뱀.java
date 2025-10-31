import java.io.*;
import java.util.*;

class Snake {
    int x, y, dir;

    public Snake(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class DirInfo {
    int seconds;
    String dir;

    public DirInfo(int seconds, String dir) {
        this.seconds = seconds;
        this.dir = dir;
    }
}

public class Main {

    static int n;
    static int[] dx = {0, 1, 0, -1};  // 동 남 서 북
    static int[] dy = {1, 0, -1, 0};
    static int[][] board;
    static LinkedList<Snake> queue = new LinkedList<>();
    static Queue<DirInfo> dirChangeInfo = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        // 사과 위치
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            board[row][col] = 2;
        }

        // 방향 전환 정보
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int seconds = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            dirChangeInfo.offer(new DirInfo(seconds, dir));
        }

        System.out.println(gameStart());
    }

    static int gameStart() {
        queue.offer(new Snake(0, 0, 0));  // 초기 위치: (0,0), 방향: 동쪽
        board[0][0] = 1;

        int timer = 0;
        while (true) {
            timer++;

            // 다음 이동 위치 계산
            Snake head = queue.peek();
            int nx = head.x + dx[head.dir];
            int ny = head.y + dy[head.dir];

            // 충돌 체크: 벽 or 자기 몸
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) break;

            // 머리 이동
            queue.offerFirst(new Snake(nx, ny, head.dir));

            // 사과 체크
            if (board[nx][ny] == 2) {
                board[nx][ny] = 1;  // 사과 먹음 → 길이 증가
            } else {
                board[nx][ny] = 1;
                Snake tail = queue.pollLast();  // 사과 없음 → 꼬리 제거
                board[tail.x][tail.y] = 0;
            }

            // 방향 전환
            if (!dirChangeInfo.isEmpty()) {
                DirInfo dirInfo = dirChangeInfo.peek();
                if (dirInfo != null && dirInfo.seconds == timer) {
                    head = queue.pollFirst();

                    if (dirInfo.dir.equals("D")) {  // 오른쪽 회전
                        head.dir = (head.dir + 1) % 4;
                    } else if (dirInfo.dir.equals("L")) {  // 왼쪽 회전
                        head.dir = (4 + head.dir - 1) % 4;
                    }

                    queue.offerFirst(new Snake(head.x, head.y, head.dir));
                    dirChangeInfo.poll();
                }
            }
        }
        return timer;
    }
}