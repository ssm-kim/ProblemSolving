import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 중복 조합
        arr = new int[m];

        dfs(0, 1);

    }

    static void dfs(int depth, int start) {
        // 기저 조건 : depth가 m에 도달하면
        if (depth == m) {
            // 현재 조합 출력
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int nextNum = start; nextNum <= n; nextNum++) {
            arr[depth] = nextNum;  // 현재 깊이에 값 저장
            dfs(depth + 1, nextNum);  // 다음 깊이로 이동, 현재 숫자는 그대로 유지
        }

    }

}
