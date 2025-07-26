import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class snake {
    int x, y;

    public snake(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class snakeDirInfo {
    int seconds;
    char dir;

    public snakeDirInfo(int seconds, char dir) {
        this.seconds = seconds;
        this.dir = dir;
    }
}

public class Main {

    static int n, k, l;

    // 동 남 서 북
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static boolean[][] visited;
    static int[][] map;
    static Queue<snakeDirInfo> snakeDirInfo = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 사과 위치
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            map[ax-1][ay-1] = 2;  // 2는 사과 위치
        }

        // 뱀의 방향 변환 정보
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            snakeDirInfo.offer(new snakeDirInfo(x, dir));
        }

        visited = new boolean[n][n];
        System.out.println(simulate());
    }

    static int simulate() {
        // 뱀 몸체 관리 덱 (앞: 머리, 뒤: 꼬리)
        LinkedList<snake> queue = new LinkedList<>();
        queue.offer(new snake(0, 0));
        visited[0][0] = true;
        int mySnakeDir = 0;  // 0:동, 1:남, 2:서, 3:북

        int seconds = 0;  // 매초 갱신
        while (true) {
            seconds++;

            // 다음 머리 위치 계산
            snake snakeHead = queue.peek();
            int nx = snakeHead.x + dx[mySnakeDir];
            int ny = snakeHead.y + dy[mySnakeDir];

            // 충돌 검사 (벽 + 자기 몸)
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) break;

            // 사과 처리
            if (map[nx][ny] == 2) {
                map[nx][ny] = 0;  // 사과 제거, 꼬리 유지 (길이 증가)
            }
            else {  // 꼬리 제거 및 방문 배열 회수 (길이 유지)
                snake snakeTail = queue.pollLast();
                visited[snakeTail.x][snakeTail.y] = false;
            }

            // 새로운 머리 추가
            visited[nx][ny] = true;
            queue.offerFirst(new snake(nx, ny));

            // 방향 전환 처리 (이동 후 체크)
            snakeDirInfo front = snakeDirInfo.peek();
            if (front != null && front.seconds == seconds) {
                if (front.dir == 'L') {
                    mySnakeDir = (mySnakeDir - 1 + 4) % 4;  // 왼쪽 방향 90도
                }
                else if (front.dir == 'D') {
                    mySnakeDir = (mySnakeDir + 1) % 4;      // 오른쪽 방향 90도
                }
                snakeDirInfo.poll();
            }
        }
        return seconds;
    }
}