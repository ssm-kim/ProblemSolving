import java.io.*;
import java.util.*;

class Node {
    int x, y, time, getGram;

    public Node(int x, int y, int time, int getGram) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.getGram = getGram;
    }
}

public class Main {

    static int n, m, t;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.time > t) continue;

            if (cur.x == n - 1 && cur.y == m - 1) {
                System.out.println(cur.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                int nextGram = cur.getGram;

                // 벽인데 그람 없으면 못감
                if (board[nx][ny] == 1 && cur.getGram == 0) continue;

                // 그람이면 상태 변경
                if (board[nx][ny] == 2) nextGram = 1;

                if (!visited[nx][ny][nextGram]) {
                    visited[nx][ny][nextGram] = true;
                    queue.offer(new Node(nx, ny, cur.time + 1, nextGram));
                }
            }
        }
        System.out.println("Fail");
    }
}