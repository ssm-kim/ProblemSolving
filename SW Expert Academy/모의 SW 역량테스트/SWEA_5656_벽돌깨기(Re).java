import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n, w, h, answer;

    // 북 돟 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] seq;
    static int[][] originBoard, board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());  // 열
            h = Integer.parseInt(st.nextToken());  // 행
            originBoard = new int[h][w];
            board = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    originBoard[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 열 기준으로 구슬을 떨어뜨리는 위치를 구한다.
            seq = new int[n];
            answer = Integer.MAX_VALUE;
            permutations(0);
            System.out.println(answer);
        }
    }

    static void permutations(int depth) {
        if (depth == n) {
            simulate();
            return;
        }  // 구슬 갯수 만큼 가능

        // 중복 순열 - 열 방향으로 떨어드릴 수 있음 (같은 방향 여러번 가능)
        for (int i = 0; i < w; i++) {
            seq[depth] = i;
            permutations(depth + 1);
        }
    }

    static void simulate() {
        // 1. 보드 복사 (원본 보존)
        recover();

        // 2. seq 배열 순서대로 구슬 떨어뜨리기
        for (int i = 0; i < n; i++) {
            int col = seq[i];
            int[] target = searchBrick(col);

            if (target[0] != -1) {
                // 3. 벽돌 깨기
                breakBricks(target[0], target[1]);

                // 4. 중력 적용
                applyGravity();
            }
        }

        int count = countBricks();
        answer = Math.min(count, answer);
    }

    static void breakBricks(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy, board[sx][sy]});
        board[sx][sy] = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int range = pos[2] - 1;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < range; j++) {
                    int nx = cx + dx[i] * (j + 1);
                    int ny = cy + dy[i] * (j + 1);

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) break;
                    if (board[nx][ny] == 0) continue;

                    queue.offer(new int[] {nx, ny, board[nx][ny]});
                    board[nx][ny] = 0;
                }
            }
        }
    }

    // 중력 적용
    static void applyGravity() {
        // 열마다 아래서부터 차곡차곡 쌓기
        for (int col = 0; col < w; col++) {
            // 각 열마다 아래에서부터 채워나가기
            int writeIdx = h - 1;  // 쓸 위치

            for (int row = h - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    board[writeIdx][col] = board[row][col];
                    if (writeIdx != row) {
                        board[row][col] = 0;
                    }
                    writeIdx--;
                }
            }
        }

        // 내 방식
//        // 밑에서부터 끌어 내린다.
//        for (int i = h - 1; i >= 0; i--) {
//            for (int j = w - 1; j >= 0; j--) {
//
//                // 빈칸이라면 위쪽 벽돌을 찾아 이까지 끌어내리기
//                if (board[i][j] == 0) {
//                    int sx = i;
//
//                    while (true) {
//                        sx--;
//                        if (sx < 0) break;  // 범위 체크
//
//                        // 만약, 벽돌이라면
//                        if (board[sx][j] != 0) {
//                            break;
//                        }
//                    }
//                    if (sx >= 0) {
//                        board[i][j] = board[sx][j];
//                        board[sx][j] = 0;
//                    }
//                }
//            }
//        }
    }

    // 벽돌 세기
    static int countBricks() {
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] != 0) count++;
            }
        }
        return count;
    }

    // 복구
    static void recover() {
        for (int i = 0; i < h; i++) {
            board[i] = Arrays.copyOf(originBoard[i], w);
        }
    }

    // 벽돌 찾기
    static int[] searchBrick(int col) {
        int row = 0;  // 행 위치
        while (true) {
            if (row >= h) return new int[] {-1, -1};  // 범위 지나가면 부술 벽돌이 없다.
            if (board[row][col] != 0) {
                break;
            }
            row++;
        }
        return new int[] {row, col};
    }
}