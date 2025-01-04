import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] arr, curPermutations;
    static boolean[] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        curPermutations = new int[m];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        // 순열
        dfs(0);
    }

    static void dfs(int depth) {
        // 기저 조건 : depth가 m에 도달하면
        if (depth == m) {
            for (int i : curPermutations) {  // 현재 순열 출력
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;  // 현재 인덱스 사용 표시
                curPermutations[depth] = arr[i];  // 현재 깊이에 값 저장
                dfs(depth + 1);  // 다음 깊이 이동
                visited[i] = false; // 백트래킹 (사용 표시 제거)
            }
        }
    }
}