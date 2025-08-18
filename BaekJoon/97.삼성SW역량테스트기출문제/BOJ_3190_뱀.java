import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class transform {
    int seconds;
    char dir;

    public transform(int seconds, char dir) {
        this.seconds = seconds;
        this.dir = dir;
    }
}

class snake {
    int x, y, dir;

    public snake(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {

    static int n;

    // 동 남 서 북 (시계 방향)
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static int[][] map;
    static Queue<transform> info = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // 인덱스 0 based
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 2;  // 사과의 위치
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int seconds = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            info.offer(new transform(seconds, dir));
        }

        LinkedList<snake> queue = new LinkedList<>();  // 뱀의 몸통 관리 (head부터 tail 순서)
        queue.offer(new snake(0, 0, 0));
        map[0][0] = 1;  // 뱀이 위치한 곳
        int time = 0;
        while (true) {
            time++;

            snake head = queue.peekLast();  // 현재 뱀 머리

            // 1. 뱀 머리를 현재 방향으로 한 칸 이동
            int nx = head.x + dx[head.dir];
            int ny = head.y + dy[head.dir];

            // 2. 게임 종료 조건 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;  // 벽 충돌
            if (map[nx][ny] == 1) break;  // 자기 몸 충돌

            // 3-1. 이동한 칸에 사과가 없다면
            if (map[nx][ny] == 2) {
                map[nx][ny] = 1;  // 다음 위치의 사과 제거 후 머리 이동
            }
            // 3-2. 이동한 칸에 사과가 없다면
            else {
                // 몸길이를 줄여서 꼬리가 위치한 칸을 비운다. 즉, 몸 길이는 변하지 않음.
                snake tail = queue.poll();
                map[tail.x][tail.y] = 0;

                map[nx][ny] = 1;  // 다음 위치로 머리 이동
            }

            // 4. 방향 전환 체크 및 새 머리 추가
            if (!info.isEmpty()) {
                transform front = info.peek();
                if (time == front.seconds) {
                    if (front.dir == 'D') {  // 오른쪽으로 90도 회전
                        head.dir = (head.dir + 1) % 4;
                    }
                    else if (front.dir == 'L') {  // 왼쪽으로 90도 회전
                        head.dir = (head.dir - 1 + 4) % 4;
                    }
                    info.poll();
                }
                queue.offer(new snake(nx, ny, head.dir));
            } else {
                queue.offer(new snake(nx, ny, head.dir));
            }

        }

        System.out.println(time);
    }
}