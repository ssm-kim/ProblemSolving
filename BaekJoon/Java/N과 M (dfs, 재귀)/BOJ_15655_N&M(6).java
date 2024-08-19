import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] arr, curCombinations;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        curCombinations = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        // 조합
        dfs(0, 0);
    }

    static void dfs(int depth, int start) {
        // 기저 조건 : depth가 m에 도달하면
        if (depth == m) {
            // 현재 조합 출력
            for (int curCombination : curCombinations) {
                System.out.print(curCombination + " ");
            }
            System.out.println();
            return;
        }

        for (int idx = start; idx < n; idx++) {
            curCombinations[depth] = arr[idx];    // 현재 깊이에 값 저장
            dfs(depth + 1, idx + 1);  // 다음 깊이 이동 및 인덱스 +1
        }
    }
}