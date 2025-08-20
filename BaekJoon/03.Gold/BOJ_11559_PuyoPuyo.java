import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final int n = 12, m = 6;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<int[]> removeBlocks;  // 한 번에 제거할 블록들

    static boolean[][] visited;
    static char[][] map = new char[n][m];

    static int chainCount = 0;  // 연쇄 횟수

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 연쇄 시뮬레이션
        while (true) {
            removeBlocks = new ArrayList<>();

            // 1. 전체 보드에서 4개 이상 연결된 뿌요 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != '.') {
                        visited = new boolean[n][m];
                        bfs(i, j);
                    }  // 문자를 찾았다면
                }
            }

            // 2. 터뜨릴 블록이 있다면 한번에 터뜨린다 (동시 제거)
            if (!removeBlocks.isEmpty()) {
                for (int[] pos : removeBlocks) {
                    int cx = pos[0];
                    int cy = pos[1];

                    map[cx][cy] = '.';
                }
                chainCount++;  // 연쇄 +1
            } else {
                break;  // 더 이상 터뜨릴 블록 없으면 종료
            }

            // 중력 작용
            downBlocks();
        }
        System.out.println(chainCount);
    }

    static void downBlocks() {
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {  // 아래에서부터 위로 탐색

                int searchX = j;
                int nextRow = j;

                // 현재 위치가 빈 칸이면 위에서 뿌요를 찾아서 내린다.
                if (map[j][i] == '.') {
                    while (true) {
                        searchX--;
                        if (searchX < 0) break;  // 맨 위까지 다 찾았으면 종료

                        // 뿌요 찾으면 아래로 이동
                        if (map[searchX][i] != '.') {
                            char tmp = map[searchX][i];
                            map[nextRow--][i] = tmp;  // 아래부터 차례로 채움
                            map[searchX][i] = '.';    // 원래 자리는 빈 공간으로
                        }
                    }
                }
            }
        }
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        visited[sx][sy] = true;

        ArrayList<int[]> searchBlocks = new ArrayList<>();
        searchBlocks.add(new int[] {sx, sy});  // 시작점 포함

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 + 방문 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

                // 같은 색 뿌요라면 연결된 것으로 처리
                if (map[sx][sy] == map[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                    searchBlocks.add(new int[] {nx, ny});
                }
            }
        }

        // 4개 이상 연결되면 제거 대상에 추가
        if (searchBlocks.size() >= 4) {
            for (int[] pos : searchBlocks) {
                removeBlocks.add(new int[] {pos[0], pos[1]});
            }
        }
    }
}