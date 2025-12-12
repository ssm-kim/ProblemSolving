import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int x, buttonPushCnt;

    public Node(int x, int buttonPushCnt) {
        this.x = x;
        this.buttonPushCnt = buttonPushCnt;
    }
}

public class Main {

    static int row;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int f = Integer.parseInt(st.nextToken());  // 총 f층
        int s = Integer.parseInt(st.nextToken());  // 강호 시작 위치
        int g = Integer.parseInt(st.nextToken());  // 목표 위치
        int u = Integer.parseInt(st.nextToken());  // 위 u만큼 이동
        int d = Integer.parseInt(st.nextToken());  // 아래 d만큼 이동

        visited = new boolean[f + 1]; // 1-based index
        row = f + 1;
        int answer = bfs(s, g, u, d);

        System.out.println(answer == -1 ? "use the stairs" : answer);
    }

    static int bfs(int s, int g, int u, int d) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(s, 0));
        visited[s] = true; // 시작점 방문 체크

        while (!queue.isEmpty()) {
            Node nd = queue.poll();

            // 목표 도달
            if (nd.x == g) {
                return nd.buttonPushCnt;
            }

            // 위/아래 버튼 2가지 이동
            for (int i = 0; i < 2; i++) {
                int nx = (i == 0) ? nd.x + u : nd.x - d; // 위 버튼 : 아래 버튼

                if (nx < 1 || nx >= row || visited[nx]) continue;

                visited[nx] = true;
                queue.offer(new Node(nx, nd.buttonPushCnt + 1));
            }
        }
        return -1; // 도달 불가능
    }
}