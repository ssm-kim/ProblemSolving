import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        arr = new int[N / 2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combinations(0, 0);
        System.out.println(answer);
    }

    static void combinations(int depth, int start) {
        if (depth == N / 2) {
            boolean[] check = new boolean[N];

            for (int i = 0; i < N / 2; i++) {
                check[arr[i]] = true;
            }  // 스타트(true) 링크(false) 분리

            int top = 0, bottom = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (check[i] && check[j])
                        top += map[i][j] + map[j][i];
                    if (!check[i] && !check[j])
                        bottom += map[i][j] + map[j][i];
                }
            }
            answer = Math.min(answer,  Math.abs(top - bottom));
            return;
        }

        for (int i = start; i < N; i++) {
            arr[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }
}