import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int N, min, max;  // N: 숫자의 개수, min: 최소 결과값, max: 최대 결과값
    static int[] numbers;    // 입력받은 숫자들을 저장할 배열
    static int[] operators;  // 연산자의 개수를 저장할 배열 (+, -, *, / 순서)

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            operators = new int[4];  // 연산자 개수 배열 초기화
            numbers = new int[N];    // 숫자 배열 초기화

            // 연산자 개수 입력 (+, -, *, / 순서)
            for (int i = 0; i < 4; i++) {
                operators[i] = sc.nextInt();
            }

            // 숫자 입력
            for (int i = 0; i < N; i++) {
                numbers[i] = sc.nextInt();
            }

            min = Integer.MAX_VALUE;  // 최소값 초기화
            max = Integer.MIN_VALUE;  // 최대값 초기화

            // dfs 호출 (첫 번째 숫자와 인덱스 1부터 시작)
            dfs(1, numbers[0]);

            // 결과 출력
            System.out.println("#" + tc + " " + (max - min));
        }
        sc.close();
    }

    // DFS를 이용한 모든 경우의 수 탐색
    static void dfs(int depth, int result) {
        // 기저 조건: 모든 숫자를 사용했을 때
        if (depth == N) {
            min = Math.min(min, result);  // 최소값 갱신
            max = Math.max(max, result);  // 최대값 갱신
            return;
        }

        // 각 연산자에 대해 반복
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {  // 해당 연산자가 남아있는 경우
                operators[i]--;  // 연산자 사용
                switch (i) {
                    case 0: dfs(depth + 1, result + numbers[depth]); break;  // 덧셈
                    case 1: dfs(depth + 1, result - numbers[depth]); break;  // 뺄셈
                    case 2: dfs(depth + 1, result * numbers[depth]); break;  // 곱셈
                    case 3: dfs(depth + 1, result / numbers[depth]); break;  // 나눗셈
                }
                operators[i]++;  // 연산자 반환 (백트래킹)
            }
        }
    }
}