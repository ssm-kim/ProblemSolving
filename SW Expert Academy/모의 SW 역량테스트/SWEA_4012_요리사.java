import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N;
    static int answer;
    static int[][] map;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            selected = new boolean[N];  // 몇 번 식재료를 선택했는지?
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            combinations(0, 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void combinations(int depth, int start) {
        if (depth == N / 2) {

            int A = 0, B = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (selected[i] && selected[j]) {
                        A += map[i][j] + map[j][i];
                    }  // A 음식 조합
                    if (!selected[i] && !selected[j]) {
                        B += map[i][j] + map[j][i];
                    }  // B 음식 조합
                }  // 식재료 선택이 3개 이상 넘어갈 땐 값을 누적해야 함.
            }

            answer = Math.min(answer, Math.abs(A - B));  // 두 음식 간의 맛의 차이
            return;
        }  // N / 2개 씩 나누어 2개의 요리를 한다.

        for (int i = start; i < N; i++) {
            selected[i] = true;
            combinations(depth + 1, i + 1);
            selected[i] = false;  // 백트래킹
        }
    }
}