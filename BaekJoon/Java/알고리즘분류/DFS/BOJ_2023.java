import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;  // 자릿수
    public static StringBuilder sb = new StringBuilder();  // 결과 저장

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // dfs (깊이 0, 시작 숫자 0)
        dfs(0, 0);

        System.out.println(sb);
    }

    // depth (노드 겸 현재 자릿수), digit (현재 숫자)
    static void dfs(int depth, int curNum) {
        // 목표 자릿수 도달 시 결과에 추가
        if (depth == N) {
            sb.append(curNum).append("\n");
            return;
        }

        // 1부터 9까지의 숫자를 현재 숫자에 추가해서 판별
        for (int i = 1; i < 10; i++) {

            // 다음 숫자 계산 (현재 숫자 * 10 + i)
            int nextNum = 10 * curNum + i;

            // 다음 숫자가 소수일 경우, 재귀 호출
            if (isPrime(nextNum)) {
                dfs(depth + 1, nextNum);
            }
        }
    }

    // 소수 판별 함수
    static boolean isPrime(int num) {
        // 0과 1은 소수가 아님
        if (num == 0 || num == 1) {
            return false;
        }

        // 2부터 sqrt(num)까지 나누어 떨어지는 수가 있는지 확인
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        // 나누어 떨어지는 수가 없으면 소수
        return true;
    }
}