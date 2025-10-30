import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
    int x, seconds;

    public Info(int x, int seconds) {
        this.x = x;
        this.seconds = seconds;
    }
}

public class Main {

    static int n, k;
    static int[] dx = {1, -1, 2};
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 최단 거리
        bfs();
    }

    static void bfs() {
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(n, 0));
        visited[n] = true;  // 내 위치

        while (!queue.isEmpty()) {
            Info info = queue.poll();

            // 만약 동생을 찾았다면 중지
            if (info.x == k) {
                System.out.println(info.seconds);
                return;
            }

            for (int i = 0; i < 3; i++) {
                // 순간이동 처리
                int nx = (i == 2) ? (info.x * dx[i]) : (info.x + dx[i]);

                if (nx < 0 || nx >= 100001 || visited[nx]) continue;

                visited[nx] = true;
                queue.offer(new Info(nx, info.seconds + 1));
            }
        }
    }
}