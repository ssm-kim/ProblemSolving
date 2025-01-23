import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Main {

    static int N, M, minTime = Integer.MAX_VALUE;
    static Queue<Node> queue = new ArrayDeque<>();
    static ArrayList<Node> batch = new ArrayList<>();
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    batch.add(new Node(i, j));
                }  // 바이러스를 놓을 수 있는 공간
            }
        }

        // 조합으로 바이러스를 놓는 모든 경우의 수를 구한다.
        combinations(0, 0, new int[M]);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    static void combinations(int depth, int start, int[] positions) {
        if (depth == M) {
            bfs(positions);
            return;
        }  // M개를 놓을 수 있다.

        for (int i = start; i < batch.size(); i++) {
            positions[depth] = i;
            combinations(depth + 1, i + 1, positions);
        }

    }

    static void bfs(int[] positions) {
        queue = new ArrayDeque<>();
        int[][] copyMap = new int[N][N];
        // 맵 초기화: 벽=-1, 나머지=0
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || map[i][j] == 2) copyMap[i][j] = 0;  // 빈 칸은 0
                if (map[i][j] == 1) copyMap[i][j] = -1;  // 벽은 -1
            }
        }

        // 초기 바이러스 위치 큐에 삽입 및 방문 처리
        for (int pos : positions) {
            Node virus = batch.get(pos);
            queue.offer(virus);
            copyMap[virus.x][virus.y] = 1;  // 방문 처리
        }

        int curTime = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();  // 현재 depth의 바이러스 개수
            boolean spread = false;   // 이번 턴에 바이러스가 퍼졌는지 체크

            for (int s = 0; s < size; s++) {
                Node nd = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || copyMap[nx][ny] != 0) continue;

                    copyMap[nx][ny] = 1;
                    queue.offer(new Node(nx, ny));
                    spread = true;
                }
            }
            if (spread) curTime++;  // 바이러스가 퍼졌을 때만 시간 증가
        }

        // 방문을 끝낸 뒤 0이 하나라도 있으면 모든 바이러스를 퍼뜨릴 수 없는 경우
        for (int[] row : copyMap) {
            for (int col : row) {
                if (col == 0) return;
            }
        }

        minTime = Math.min(minTime, curTime);
    }
}