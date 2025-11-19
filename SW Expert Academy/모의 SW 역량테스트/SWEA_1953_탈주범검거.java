import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n, m;

    // 북(0) 동(1) 남(2) 서(3) 방향 정의
    static int[][] typeX = {
        {},
        {-1, 0, 1, 0},  // 1번: 북 동 남 서 (4방향)
        {-1, 1},        // 2번: 북남
        {0, 0},         // 3번: 동서
        {-1, 0},        // 4번: 북동
        {0, 1},         // 5번: 동남
        {1, 0},         // 6번: 남서
        {0, -1}         // 7번: 서북
    };
    static int[][] typeY = {
        {},
        {0, 1, 0, -1},  // 1번: 북 동 남 서
        {0, 0},         // 2번: 북남
        {1, -1},        // 3번: 동서
        {0, 1},         // 4번: 북동
        {1, 0},         // 5번: 동남
        {0, -1},        // 6번: 남서
        {-1, 0}         // 7번: 서북
    };

    // 각 파이프가 가진 방향 (typeX, typeY와 순서 일치)
    static HashMap<Integer, int[]> map = new HashMap<Integer, int[]>() {
        {
            put(1, new int[] {0, 1, 2, 3});  // 북동남서
            put(2, new int[] {0, 2});        // 북남
            put(3, new int[] {1, 3});        // 동서
            put(4, new int[] {0, 1});        // 북동
            put(5, new int[] {1, 2});        // 동남
            put(6, new int[] {2, 3});        // 남서
            put(7, new int[] {3, 0});        // 서북
        }
    };

    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());  // 시작 x좌표
            int c = Integer.parseInt(st.nextToken());  // 시작 y좌표
            int l = Integer.parseInt(st.nextToken());  // 탈출 시간

            board = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[n][m];
            bfs(r, c, l);

            // 방문 가능한 위치 카운트
            int posCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j]) posCnt++;
                }
            }
            System.out.println("#" + t + " " + posCnt);
        }
    }

    static void bfs(int r, int c, int l) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c, 1});  // 시작 시간은 1
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int timer = pos[2];
            int pipeType = board[cx][cy];

            // 시간 초과 시 중단
            if (timer == l) break;

            // 현재 파이프가 갈 수 있는 모든 방향 탐색
            int dirCnt = typeX[pipeType].length;
            for (int i = 0; i < dirCnt; i++) {
                int nx = cx + typeX[pipeType][i];
                int ny = cy + typeY[pipeType][i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 방문 체크 + 파이프 존재 체크
                if (visited[nx][ny] || board[nx][ny] == 0) continue;

                // 파이프 연결 가능 여부 체크
                if (isConnect(pipeType, board[nx][ny], i)) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, timer + 1});
                }
            }
        }
    }

    // 현재 파이프와 다음 파이프가 연결 가능한지 체크
    static boolean isConnect(int curPipeType, int nextPipeType, int dir) {
        int currentDir = map.get(curPipeType)[dir];  // 내가 간 방향
        int reverseDir = (currentDir + 2) % 4;       // 반대 방향

        // 다음 파이프가 반대 방향을 가지고 있는지 확인
        int[] nextDirs = map.get(nextPipeType);
        for (int nextDir : nextDirs) {
            if (nextDir == reverseDir) return true;
        }
        return false;
    }
}