import java.io.*;
import java.util.*;

public class Main {

    static final int SELECT = 3;
    static int N, M, answer;
    static boolean[][] notMix;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        notMix = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            notMix[a][b] = notMix[b][a] = true;
        }  // 양방향 설정

        answer = 0;
        combinations(0, 0, new int[SELECT]);
        System.out.println(answer);
    }

    static void combinations(int depth, int start, int[] kinds) {
        if (depth == SELECT) {
            for (int i = 0; i < SELECT; i++) {
                for (int j = i + 1; j < SELECT; j++) {
                    if (notMix[kinds[i]][kinds[j]]) {
                        return;
                    }
                }
            }
            answer++;
            return;
        }  // 3가지 선택

        for (int i = start; i < N; i++) {
            kinds[depth] = i + 1;
            combinations(depth + 1, i + 1, kinds);
        }
    }
}