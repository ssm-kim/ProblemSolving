import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n, q, maxIceSize;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        q = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Q번의 파이어스톰 실행
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(st.nextToken());
            int grid = (int) Math.pow(2, l);  // 2^L 크기의 격자

            fireStorm(grid);
        }

        // 1. 남은 얼음의 총합 계산
        int totalIce = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalIce += map[i][j];
            }
        }

        // 2. 가장 큰 얼음 덩어리 크기 찾기
        searchBigIce();

        System.out.println(totalIce + "\n" + maxIceSize);
    }

    static void fireStorm(int grid) {
        // 1단계: 모든 2^L × 2^L 격자를 시계방향 90도 회전
        for (int i = 0; i < n; i+=grid) {
            for (int j = 0; j < n; j+=grid) {
                rotate(i, j, grid);
            }
        }

        // 2단계: 인접한 얼음이 3개 미만인 칸의 얼음 1씩 감소
        meltIce();
    }

    static void searchBigIce() {
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 얼음 존재 + 방문 X
                if (map[i][j] > 0 && !visited[i][j]) {
                    int curIceSize = bfs(i, j, visited);
                    maxIceSize = Math.max(maxIceSize, curIceSize);
                }
            }
        }
    }

    static int bfs(int sx, int sy, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy));
        visited[sx][sy] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            Node nd = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = nd.x + dx[k];
                int ny = nd.y + dy[k];

                // 범위 체크, 방문 체크, 얼음 존재 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] <= 0) continue;

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny));
                size++;
            }
        }
        return size;
    }

    // 얼음 녹이기: 인접한 얼음이 3개 미만인 칸 찾아서 동시에 1씩 감소
    static void meltIce() {
        ArrayList<int[]> meltTargets = new ArrayList<>();

        // 녹을 대상 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int iceCnt = 0;  // 얼음 갯수
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    // 범위 체크 + 얼음 양 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] <= 0) continue;

                    iceCnt++;
                }

                // 인접한 얼음이 3개 미만이면 녹을 대상
                if (iceCnt < 3) {
                    meltTargets.add(new int[] {i, j});
                }
            }
        }

        // 동시에 녹인다.
        for (int[] coordinate : meltTargets) {
            int x = coordinate[0];
            int y = coordinate[1];
            map[x][y] = (map[x][y] == 0) ? 0 : map[x][y] - 1;
        }
    }

    static void rotate(int sx, int sy, int size) {
        // 원본 격자를 임시 배열에 복사
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = map[i + sx][j + sy];
            }
        }

        // 동심원별로 회전 처리 (바깥쪽부터 안쪽까지)
        for (int layer = 0; layer < (size / 2); layer++) {
            for (int i = layer; i < size - layer; i++) {
                // 시계방향 90도 회전: 각 변이 다음 변으로 이동

                // 위쪽 행 -> 오른쪽 열
                map[sx + i][sy + size - 1 - layer] = temp[layer][i];

                // 오른쪽 열 -> 아래쪽 행
                map[sx + size - 1 - layer][sy + size - 1 - i] = temp[i][size - 1 - layer];

                // 아래쪽 행 -> 왼쪽 열
                map[sx + i][sy + layer] = temp[size - 1 - layer][i];

                // 왼쪽 열 -> 위쪽 행
                map[sx + layer][sy + size - 1 - i] = temp[i][layer];
            }
        }
    }
}