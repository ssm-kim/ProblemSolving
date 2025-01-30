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
}

public class Main {

    static int N, M, safety = Integer.MIN_VALUE;
    static ArrayList<Node> virus = new ArrayList<>();
    static int[][] map, copyMap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Node(i, j));
            }
        }

        combinations(0);
        System.out.println(safety);
    }

    static void combinations(int depth) {
        // 벽 3개를 모두 세웠으면 바이러스 전파 시뮬레이션 시작
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;  // 벽 설치
                    combinations(depth + 1);
                    map[i][j] = 0;  // 벽 제거 (백트래킹)
                }
            }
        }
    }

    static void bfs() {
        // 기존 맵 복사하여 시뮬레이션용 맵 생성
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        // 초기 바이러스 위치를 큐에 저장
        Queue<Node> queue = new LinkedList<>();
        for (Node node : virus) queue.offer(node);

        // BFS 바이러스 전파 시뮬레이션
        while (!queue.isEmpty()) {
            Node pos = queue.poll();
            int cx = pos.x;
            int cy = pos.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N ||  ny < 0 || ny >= M) continue;

                // 빈 공간이면 바이러스 전파
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    queue.offer(new Node(nx ,ny));
                }
            }
        }
        // 안전 영역 계산
        int curSafety = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) curSafety++;
            }
        }
        safety = Math.max(safety, curSafety);
    }
}