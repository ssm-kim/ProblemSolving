import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int n;
    static int[] S, B;
    static int minScore = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        S = new int[n];  // 신맛 (곱셈)
        B = new int[n];  // 쓴맛 (덧셈)

        for (int i = 0; i < n; i++) {
            S[i] = sc.nextInt();
            B[i] = sc.nextInt();
        }

        // dfs (depth 0, 초기 신맛 1, 초기 쓴맛 0, 재료 사용 여부)
        dfs(0, 1, 0, false);

        System.out.println(minScore);
    }

    static void dfs(int depth, int sum_S, int sum_B, boolean check) {

        // 기저 조건 : 최대 깊이에 도달 (완탐)
        if (depth == n) {
            if (check) {  // 최소 하나의 재료 선택 시 최솟값 갱신
                int diff = Math.abs(sum_S - sum_B);
                minScore = Math.min(minScore, diff);
            }
            return;
        }

        // 현재 깊이에서 재료 사용 O
        dfs(depth + 1, sum_S * S[depth], sum_B + B[depth], true);

        // 백트래킹: 부모 노드로 올라 가면서 현재 재료 사용 X, 다음 재료 사용 O
        dfs(depth + 1, sum_S, sum_B, check);
    }
}