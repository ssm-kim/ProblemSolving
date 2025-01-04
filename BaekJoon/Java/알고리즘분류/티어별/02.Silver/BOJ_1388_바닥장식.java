import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

// 단순 반복문 구현
/* public class Main {

    static int r, c, answer;
    static char[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            char[] tmp = sc.next().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp[j];
            }
        }

        // '-' 확인
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '-') {
                    answer++;
                    while (j < c && map[i][j] == '-') {
                        j++;
                    }
                }
            }
        }

        // '|' 확인
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (map[j][i] == '|') {
                    answer++;
                    while (j < r && map[j][i] == '|') {
                        j++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
 */


// DFS 활용
public class Main {

    static int r, c, answer, check;
    static int[] dir = {1, -1};
    static boolean[][] visited;
    static char[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            char[] tmp = sc.next().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp[j];
            }
        }

        // check == 1일 때 '|' 확인
        visited = new boolean[r][c];
        check = 1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && map[i][j] == '-') { // 방문X + 현재 값 '-'일 떄
                    answer++;
                    dfs(i, j);
                }
            }
        }

        // visited 초기화
        for (int i = 0; i < r; i++) {
            Arrays.fill(visited[i], false);
        }

        // check == 2일 때 '|' 확인
        check = 2;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (!visited[j][i] && map[j][i] == '|') { // 방문X + 현재 값 '|'일 떄
                    answer++;
                    dfs(j, i);
                }
            }
        }
        System.out.println(answer);
    }

    static void dfs(int sx, int sy) {
        if (check == 1) {
            visited[sx][sy] = true;

            for (int i = 0; i < 2; i++) {
                int ny = sy + dir[i];
                if (ny < 0 || ny >= c) {  // 범위 검사
                    continue;
                }

                if (!visited[sx][ny] && map[sx][ny] == '-') {  // 행 검사
                    dfs(sx, ny);
                }
            }
        } else if (check == 2) {
            visited[sx][sy] = true;

            for (int i = 0; i < 2; i++) {
                int nx = sx + dir[i];
                if (nx < 0 || nx >= r) {  // 범위 검사
                    continue;
                }

                if (!visited[nx][sy] && map[nx][sy] == '|') {  // 열 검사
                    dfs(nx, sy);
                }
            }
        }
    }
}
