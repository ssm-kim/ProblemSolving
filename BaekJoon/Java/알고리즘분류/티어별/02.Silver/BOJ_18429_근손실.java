import java.io.*;
import java.util.*;

public class Main {

    static final int maintain = 500;
    static int N, K, answer = 0;
    static int[] weights;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 운동 키트 종류
        K = Integer.parseInt(st.nextToken());  // 하루 지날 떄마다 K만큼 중량 감소

        weights = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        permutations(0, new int[N]);  // N개 중에 N를 선택하는 서로 다른 순열 (중복 X)

        System.out.println(answer);
    }

    static void permutations(int depth, int[] seq) {
        if (depth == N) {
            int curWeight = 500;
            System.out.println(Arrays.toString(visited));
            for (int day = 0; day < N; day++) {
                curWeight -= K;  // 매일 K만큼 중량이 빠짐.
                curWeight += weights[seq[day]];

                if (maintain > curWeight) return;  // 중량이 무조건 500 이상 유지 되어야 함
            }
            answer++;  // 운동을 다해도 중량이 500 밑으로 되지 않으면 answer++
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[depth] = i;
                permutations(depth + 1, seq);
                visited[i] = false;
            }
        }
    }
}