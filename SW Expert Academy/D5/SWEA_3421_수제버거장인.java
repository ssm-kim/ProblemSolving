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