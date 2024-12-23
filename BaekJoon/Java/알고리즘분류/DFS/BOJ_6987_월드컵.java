import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[6][3];
    static int[] team1 = new int[15];
    static int[] team2 = new int[15];
    static int[] answer = new int[4];
    static boolean check;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 0; tc < 4; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int idx = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    team1[idx] = i;
                    team2[idx] = j;
                    idx++;
                }
            }  // 가능한 모든 경기의 조합

            check = false;
            dfs(0);
            if (check) answer[tc]++;
        }

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    static void dfs(int depth) {
        if (depth == 15) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (map[i][j] != 0) return;
                }
            }

            // 승 무 패 전부 0 이면
            check = true;
            return;
        }

        int t1 = team1[depth];
        int t2 = team2[depth];

        // t1 승리 t2 패배  ( t1팀이 승리할 수 있는 경우만 탐색 )
        if (map[t1][0] > 0 && map[t2][2] > 0) {
            map[t1][0]--;  // t1의 승리 횟수 감소
            map[t2][2]--;  // t2의 패배 횟수 감소
            dfs(depth + 1);
            map[t1][0]++;  // 복구
            map[t2][2]++;  // 복구
        }

        // t2 승리 t1 패배
        if (map[t2][0] > 0 && map[t1][2] > 0) {
            map[t1][2]--;
            map[t2][0]--;
            dfs(depth + 1);
            map[t1][2]++;
            map[t2][0]++;
        }

        // 무승부
        if (map[t1][1] > 0 && map[t2][1] > 0) {
            map[t1][1]--;
            map[t2][1]--;
            dfs(depth + 1);
            map[t1][1]++;
            map[t2][1]++;
        }
    }
}