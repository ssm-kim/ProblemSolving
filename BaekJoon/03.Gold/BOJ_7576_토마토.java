import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;
    int cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {

    static int N, M, answer = -1;
    static int[][] map;
    static LinkedList<Node> queue = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.offer(new Node(i, j, 0));
                }  // 출발지가 여러군데일 가능성 존재
            }
        }

        bfs();

        loop: for (int[] rows : map) {
            for (int col : rows) {
                if (col == 0) {
                    answer = -1;
                    break loop;
                }  // 토마토가 모두 익지 못하는 상황이라면
            }
        }
        System.out.println(answer);
    }

    static void bfs() {

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            answer = Math.max(answer, node.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == -1) continue;

                if (map[node.x][node.y] == 1 && map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    queue.offer(new Node(nx, ny, node.cnt + 1));
                }  // 현재 토마토가 익은 토마토(1)이고 인접한 상하좌우 칸에 익지 않은 토마토(0)가 있다면 퍼뜨린다.
            }
        }
    }
}