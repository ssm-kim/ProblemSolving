import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int n, m;
    static boolean[] visited;  // 각 숫자 사용 여부 확인
    static int[] arr;  // 현재 순열 저장 배열

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();  // 전체 숫자 개수
        m = sc.nextInt();  // 선택할 숫자 개수

        visited = new boolean[n];
        arr = new int[m];
        dfs(0, 1);  // 깊이 0부터 dfs
    }

    static void dfs(int depth, int cur_idx) {
        if (depth == m) {  // 기저 조건 : m개의 숫자를 모두 선택했을 때
            for (int i : arr) {  // 현재 조합 출력
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        // cur_idx(1) 부터 n까지 반복
        for (int next_idx = cur_idx; next_idx < n + 1; next_idx ++) {
            // 현재 깊이에 숫자 선택
            arr[depth] = next_idx;

            // depth 증가 시 다음 시작 인덱스는 현재 선택한 숫자 다음부터
            dfs(depth + 1, next_idx + 1);
        }
    }
}