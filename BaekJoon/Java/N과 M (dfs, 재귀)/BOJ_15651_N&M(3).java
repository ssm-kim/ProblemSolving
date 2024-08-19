import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 중복 순열.
        arr = new int[m];  // 크기가 m인 배열 초기화
        dfs(0);     // dfs 시작

    }

    static void dfs(int depth) {
        // 기저 조건 : depth가 m에 도달하면
        if (depth == m) {
            for (int i : arr) {  // 배열에 저장된 수열 출력
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int next_v = 1; next_v <= n; next_v++) {  // 1부터 n까지 반복
            arr[depth] = next_v;    // 현재 깊이에 숫자 저장
            dfs(depth + 1);  // 다음 깊이로 이동
        }
    }
}
