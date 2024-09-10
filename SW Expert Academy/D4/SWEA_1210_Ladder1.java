import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static boolean check;
    static int answer;
    static int[][] map;
    static int[] dx = {0,  0, 1};  // 좌 우 아래
    static int[] dy = {1, -1, 0};

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            tc = sc.nextInt();

            map = new int[100][100];
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            check = false;
            answer = 0;
            for (int i = 0; i < 100; i++) {
                if (map[0][i] == 1) {  // 사다리를 탈 수 있다면
                    dfs(0, i);
                    if (check) {
                        answer = i;
                        break;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int cx, int cy) {
        if (cx == 100 - 1 && map[cx][cy] == 2) {
            check = true;
            return;
        }  // 도착지점을 찾음

        map[cx][cy] = 0;  // 방문 체크
        for (int i = 0; i < 3; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100) {
                continue;
            }  // 범위 검사

            if(map[nx][ny] > 0) {  // 1 또는 2일 때만 + 좌 우 무한루프 (생각)
                dfs(nx, ny);
                break;
            }
        }
        map[cx][cy] = 1;  // 다음 탐색을 위해 복구 (백트래킹)
    }
}