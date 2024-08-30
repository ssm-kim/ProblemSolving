import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int n, m, answer, maxHome = 0;
    static ArrayList<int[]> cStores = new ArrayList<>();
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    maxHome++;
                }
                if (map[i][j] == 2) { // 탐색할 집 좌표 저장
                    cStores.add(new int[]{i, j, 0});
                }
            }
        }

        answer = Integer.MAX_VALUE;
        int[] select = new int[m];
        dfs(0, 0, select);
        System.out.println(answer);
    }

    static void dfs(int depth, int start, int[] select) {
        if (depth == m) {
            bfs(select);
            return;
        }

        // 총 치킨집 갯수 중에 m개를 뽑는 조합
        for (int i = start; i < cStores.size(); i++) {
            select[depth] = i;
            dfs(depth + 1, i + 1, select);
        }
    }

    static void bfs(int[] sPoints) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] minDistance = new int[n][n];
        for (int[] row : minDistance) {  // 최소 값을 찾기 위해 int max 값 초기화
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int p : sPoints) {
            int[] tmp = cStores.get(p);
            queue.offer(tmp);
            minDistance[tmp[0]][tmp[1]] = 0;  // 인덱스 0, 1 좌표이며, 2는 거리
        }

        int totalDistance = 0, searchHome = 0;
        loop: while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int move = pos[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nextMove = move + 1;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {  // 범위 검사
                    continue;
                }

                if (maxHome == searchHome) {  // 조기 종료
                    break loop;
                }

                if (nextMove < minDistance[nx][ny]) {
                    minDistance[nx][ny] = nextMove;
                    queue.offer(new int[]{nx, ny, nextMove});

                    if (map[nx][ny] == 1) {  // 집을 찾았을 때
                        totalDistance += nextMove;
                        searchHome++;
                    }
                }
            }
        }
        answer = Math.min(answer, totalDistance);  // 최소 거리 갱신
    }
}