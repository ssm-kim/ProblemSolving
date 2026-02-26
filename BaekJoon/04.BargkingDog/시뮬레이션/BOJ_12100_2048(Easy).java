import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int n, maxMoveCnt = 5, answer;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        backTrack(0);
        System.out.println(answer);
    }

    // 최대 5번 이동으로 만들 수 있는 가장 큰 블록 찾기
    static void backTrack(int depth) {
        if (depth == maxMoveCnt) {
            // 현재 보드에서 최댓값 찾기
            int maxBlockSize = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maxBlockSize = Math.max(maxBlockSize, board[i][j]);
                }
            }
            answer = Math.max(answer, maxBlockSize);
            return;
        }

        // 4방향 모두 시도
        for (int dir = 0; dir < 4; dir++) {
            int[][] backup = copyBoard();  // 보드 백업
            blockMove(dir);                // 이동
            backTrack(depth + 1);          // 재귀
            board = backup;                // 복원
        }
    }

    static int[][] copyBoard() {
        int[][] copyBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        return copyBoard;
    }

    static void blockMove(int dir) {
        switch (dir) {
            case 0 : moveNorth(); break;
            case 1 : moveSouth(); break;
            case 2 : moveWest(); break;
            case 3 : moveEast(); break;
        }
    }

    static void moveEast() {
        // 각 행별로 0이 아닌 값들 추출
        LinkedList<Integer>[] union = new LinkedList[n];
        for (int i = 0; i < n; i++) union[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    union[i].add(board[i][j]);
                }
            }
        }

        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 오른쪽부터 처리하므로 뒤에서부터 꺼냄
            while (!union[i].isEmpty()) {
                int current = union[i].pollLast();

                if (union[i].isEmpty()) {
                    tmp.add(current);
                    break;
                }

                int next = union[i].peekLast();
                if (current == next) {
                    tmp.add(current * 2);  // 합치기
                    union[i].pollLast();
                }
                else {
                    tmp.add(current);
                }
            }

            // 오른쪽부터 채우기
            for (int j = n - 1; j >= 0; j--) {
                board[i][j] = (!tmp.isEmpty()) ? tmp.poll() : 0;
            }
            tmp.clear();
        }
    }

    static void moveWest() {
        // 각 행별로 0이 아닌 값들 추출
        LinkedList<Integer>[] union = new LinkedList[n];
        for (int i = 0; i < n; i++) union[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    union[i].add(board[i][j]);
                }
            }
        }

        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 왼쪽부터 처리하므로 앞에서부터 꺼냄
            while (!union[i].isEmpty()) {
                int current = union[i].poll();

                if (union[i].isEmpty()) {
                    tmp.add(current);
                    break;
                }

                int next = union[i].peekFirst();
                if (current == next) {
                    tmp.add(current * 2);  // 합치기
                    union[i].poll();
                }
                else {
                    tmp.add(current);
                }
            }

            // 왼쪽부터 채우기
            for (int j = 0; j < n; j++) {
                board[i][j] = (!tmp.isEmpty()) ? tmp.poll() : 0;
            }
            tmp.clear();
        }
    }

    static void moveSouth() {
        // 각 열별로 0이 아닌 값들 추출
        LinkedList<Integer>[] union = new LinkedList[n];
        for (int i = 0; i < n; i++) union[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] != 0) {
                    union[i].add(board[j][i]);
                }
            }
        }

        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 아래쪽부터 처리하므로 뒤에서부터 꺼냄
            while (!union[i].isEmpty()) {
                int current = union[i].pollLast();

                if (union[i].isEmpty()) {
                    tmp.add(current);
                    break;
                }

                int next = union[i].peekLast();
                if (current == next) {
                    tmp.add(current * 2);  // 합치기
                    union[i].pollLast();
                }
                else {
                    tmp.add(current);
                }
            }

            // 아래쪽부터 채우기
            for (int j = n - 1; j >= 0; j--) {
                board[j][i] = (!tmp.isEmpty()) ? tmp.poll() : 0;
            }
            tmp.clear();
        }
    }

    static void moveNorth() {
        // 각 열별로 0이 아닌 값들 추출
        LinkedList<Integer>[] union = new LinkedList[n];
        for (int i = 0; i < n; i++) union[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] != 0) {
                    union[i].add(board[j][i]);
                }
            }
        }

        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 위쪽부터 처리하므로 앞에서부터 꺼냄
            while (!union[i].isEmpty()) {
                int current = union[i].poll();

                if (union[i].isEmpty()) {
                    tmp.add(current);
                    break;
                }

                int next = union[i].peekFirst();
                if (current == next) {
                    tmp.add(current * 2);  // 합치기
                    union[i].poll();
                }
                else {
                    tmp.add(current);
                }
            }

            // 위쪽부터 채우기
            for (int j = 0; j < n; j++) {
                board[j][i] = (!tmp.isEmpty()) ? tmp.poll() : 0;
            }
            tmp.clear();
        }
    }
}