import java.io.*;
import java.util.*;

class Node {
    int number, x, y;

    public Node(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n;
    static boolean[] visited;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());  // 편의점 개수
            nodes = new Node[n + 2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            nodes[0] = new Node(0, sx, sy);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int px = Integer.parseInt(st.nextToken());
                int py = Integer.parseInt(st.nextToken());
                nodes[i + 1] = new Node(i + 1, px, py);
            }

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            nodes[n + 1] = new Node(n + 1, ex, ey);

            System.out.println(bfs());
        }
    }

    static String bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(nodes[0]); // 시작점(집)을 큐에 넣고

        visited = new boolean[n + 2];
        visited[nodes[0].number] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 현재 노드에서 모든 노드로 이동 가능한지 확인
            for (int i = 0; i < n + 2; i++) {
                if (!visited[i]) {
                    // 맨해튼 거리 계산
                    int distance = Math.abs(cur.x - nodes[i].x) + Math.abs(cur.y - nodes[i].y);

                    if (distance > 1000) continue; // 맥주 20병 × 50m = 1000m 초과하면 못 감

                    if (i == n + 1) return "happy"; // 페스티벌 도착

                    visited[i] = true;
                    queue.offer(nodes[i]);
                }
            }
        }
        return "sad"; // 큐가 비면 도달 불가
    }
}