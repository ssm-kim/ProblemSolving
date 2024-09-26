import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;


/**
 * HashSet를 이용한 구현
 * 1. 중복 체크 구현이 필요없음.
 * 2. 해시 테이블 기반이므로 속도가 빠르다. 평균적으로 O(1)
 * 3. 메모리 사용량이 많다.
 */

public class Main {

    static int N, M, answer = 0;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[] visited = new boolean[26];  // 알파벳 26개에 대한 방문 여부
    // static ArrayList<Character> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j];
            }
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int cx, int cy, int count) {

        answer = Math.max(answer, count);

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            int idx = map[nx][ny] - 'A';
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(nx, ny, count + 1);
                visited[idx] = false;
            }
        }
    }
}

//public class Main {
//    static int R, C, maxLen;
//    static char[][] board;
//    static int[] dx = {0, 0, 1, -1};
//    static int[] dy = {1, -1, 0, 0};
//    static HashSet<Character> alphaSet = new HashSet<>();
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        R = Integer.parseInt(st.nextToken());
//        C = Integer.parseInt(st.nextToken());
//        board = new char[R][C];
//
//        for (int i = 0; i < R; i++) {
//            char[] tmp = br.readLine().toCharArray();
//            for (int j = 0; j < tmp.length; j++) {
//                board[i][j] = tmp[j];
//            }
//        }
//
//        dfs(1, 0, 0);
//        System.out.println(maxLen);
//    }
//
//    static void dfs(int depth, int cx, int cy) {
//        // 기저 조건 : 현재 알파벳이 이미 사용된 경우
//        if (alphaSet.contains(board[cx][cy])) {
//            return;
//        }
//
//        // 현재 알파벳 추가
//        alphaSet.add(board[cx][cy]);
//        maxLen = Math.max(maxLen, depth);
//
//        for (int i = 0; i < 4; i++) {
//            int nx = cx + dx[i];
//            int ny = cy + dy[i];
//
//            // 범위 검사
//            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
//                continue;
//            }
//            dfs(depth + 1, nx, ny);
//        }
//        // 현재 알파벳 제거 (백트래킹)
//        alphaSet.remove(board[cx][cy]);
//    }
//}

/**
 * 아래는 ArrayList와 visited 2차원 배열을 통한 구현
 * 1. 중복 체크를 직접 수행해야하므로 느리다.
 * 2. 리스트는 순차적 탐색을 하므로 O(n)의 시간 복잡도를 가진다. (느림)
 * 3. 메모리 사용량이 적다.

public class Main {

    static int R, C, maxLen;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Character> alphas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < tmp.length; j++) {
                board[i][j] = tmp[j];
            }
        }

        visited = new boolean[R][C];
        dfs(1, 0, 0);
        System.out.println(maxLen);

    }

    static void dfs(int depth, int cx, int cy) {
        // 기저 조건 : 현재 알파벳이 이미 사용된 경우
        if (alphas.contains(board[cx][cy])) {
            return;
        }

        // 현재 알파벳 추가
        alphas.add(board[cx][cy]);
        visited[cx][cy] = true;
        maxLen = Math.max(maxLen, depth);

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 검사
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            // 방문 여부 검사 & 현재 알파벳 누적
            if (!visited[nx][ny]) {
                dfs(depth + 1, nx, ny);

            }
        }
        // 현재 알파벳 제거 및 방문 해제 (백트래킹)
        alphas.remove(alphas.size() - 1);
        visited[cx][cy] = false;
    }
}

*/