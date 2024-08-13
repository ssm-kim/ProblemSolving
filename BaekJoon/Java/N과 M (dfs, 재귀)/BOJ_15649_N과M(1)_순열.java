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
        dfs(0);  // 깊이 0부터 dfs
    }

    static void dfs(int depth) {
        if (depth == m) {  // 깊이가 m에 도달 했으면 순열 출력
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int next_idx = 0; next_idx < n; next_idx++) {
            if (!visited[next_idx]) {  // 아직 방문하지 않았다면
                visited[next_idx] = true;   // 방문 표시
                arr[depth] = next_idx + 1;  // 현재 깊이에 숫자 저장
                dfs(depth + 1);       // 다음 깊이로 재귀 호출
                visited[next_idx] = false;  // 방문 표시 제거
            }
        }
    }
}