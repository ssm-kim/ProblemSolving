import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int count;
    public Point(int x, int count) {
        this.x = x;
        this.count = count;
    }
}

public class Main {

    static int n, m;
    static boolean[] visited;
    static int[] board = new int[101];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        visited = new boolean[101];
        int answer = bfs(1);
        System.out.println(answer);
    }

    static int bfs(int sx) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx, 0));
        visited[sx] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int cx = p.x;
            int curCount = p.count;

            if (cx == 100) return curCount;

            // 주사위 1 ~ 6 굴리기
            for (int diceNumber = 1; diceNumber <= 6; diceNumber++) {
                int nx = cx + diceNumber;
                if (nx < 1 || nx >= 101 || visited[nx]) continue;

                // 다음 좌표가 순간 이동 좌표 ( 사다리 또는 뱀 )일 때
                if (board[nx] > 0) {
                    visited[board[nx]] = true;
                    queue.offer(new Point(board[nx], curCount + 1));
                    continue;
                }

                // 다음 좌표가 0일 때
                if (board[nx] == 0) {
                    visited[nx] = true;
                    queue.offer(new Point(nx, curCount + 1));
                }
            }
        }
        return -1;
    }
}