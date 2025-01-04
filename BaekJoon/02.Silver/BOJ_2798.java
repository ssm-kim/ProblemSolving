import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N, M, answer, diff;
    static int[] arr;
    static int[] cards;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        cards = new int[N];

        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }

        // N개 중에 3개를 뽑는 조합 (중복 X)
        arr = new int[3];
        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int depth, int start) {
        // 기저 조건 : depth가 3에 도달하면
        if (depth == 3) {
            // M을 넘지 않으면 최대한 가까운 카드 3장의 합을 구한다.
            int sum = cards[arr[0]] + cards[arr[1]] + cards[arr[2]];  // 카드의 합

            // 선택된 조합의 합이 M에 최대한 가까우면 값 갱신
            if (sum <= M) {
                answer = Math.max(answer, sum);
            }
            return;
        }

        for (int nextIdx = start; nextIdx < N; nextIdx++) {
            arr[depth] = nextIdx;
            dfs(depth + 1, nextIdx + 1);
        }
    }

}