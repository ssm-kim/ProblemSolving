import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, answer;
    static Set<int[]> set;
    static int[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            set = new HashSet<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                set.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            }

            map = new int[N + 1];
            answer = 0;
            subset(1);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void subset(int depth) {
        if (depth == N + 1) {
            boolean used = false;
            for (int[] next : set) {
                int a = next[0];
                int b = next[1];

                if (map[a] == 1 && map[b] == 1) used = true;

                if (used) {
                    break;
                }
            }

            if (!used) answer++;
            return;
        }

        map[depth]++;
        subset(depth + 1);

        map[depth]--;
        subset(depth + 1);
    }
}


/*
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

public class Solution {
    static int N, M, answer;
    static int[][] pairs;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            pairs = new int[M][2];
            selected = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                pairs[i][0] = Integer.parseInt(st.nextToken());
                pairs[i][1] = Integer.parseInt(st.nextToken());
            }

            answer = 0;
            subset(1);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void subset(int depth) {
        if (depth == N + 1) {
            answer++;
            return;
        }

        // 1. 현재 재료를 선택하지 않는 경우
        subset(depth + 1);

        // 2. 현재 재료를 선택할 수 있는지 확인
        if (isValid(depth)) {
            selected[depth] = true;  // 현재 재료 선택
            subset(depth + 1);  // 다음 재료 고려하기
            selected[depth] = false;  // 백트래킹: 선택 취소
        }
    }

    private static boolean isValid(int depth) {
        for (int[] pair : pairs) {
            if (pair[0] == depth && selected[pair[1]]) return false;
            if (pair[1] == depth && selected[pair[0]]) return false;
        }
        return true;
    }
}
*/