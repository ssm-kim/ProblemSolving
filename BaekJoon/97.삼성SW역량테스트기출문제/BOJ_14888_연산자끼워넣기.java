import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] numbers;
    static int[] seq;
    static int[] operator;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operator = new int[4];
        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                switch (i) {
                    case 0: operator[0]++; break;
                    case 1: operator[1]++; break;
                    case 2: operator[2]++; break;
                    case 3: operator[3]++; break;
                }
            }
        }
        seq = new int[N - 1];
        permutations(0);
        System.out.println(max + "\n" + min);
    }

    // 백트래킹 순열
    static void permutations(int depth) {
        if (depth == N - 1) {

            int res = calculate();

            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;  // 연산자 사용
                seq[depth] = i;
                permutations(depth + 1);
                operator[i]++;  // 연산자 복구
            }
        }
    }

    static int calculate() {
        int res = numbers[0];  // 첫 번째 숫자로 초기화

        for (int i = 0; i < N - 1; i++) {
            switch (seq[i]) {
                case 0: res += numbers[i + 1];
                    break;
                case 1: res -= numbers[i + 1];
                    break;
                case 2: res *= numbers[i + 1];
                    break;
                case 3: res /= numbers[i + 1];
                    break;
            }
        }
        return res;
    }
}

/*
    백트래킹 풀이

    public class Main {

        static int n;
        static int[] answer;
        static int[] numbers;
        static int[] operatorCounts = new int[4]; // +, -, *, / 개수

        public static void main(String[] args) throws IOException {
            System.setIn(new FileInputStream("input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            numbers = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            // 연산자 개수 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operatorCounts[i] = Integer.parseInt(st.nextToken());
            }

            answer = new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE};
            backtrack(0, numbers[0]);

            System.out.println(answer[0] + "\n" + answer[1]);
        }

        static void backtrack(int depth, int curResult) {
            // 모든 연산자를 사용했다면 결과 갱신
            if (depth == n - 1) {
                answer[0] = Math.max(answer[0], curResult);
                answer[1] = Math.min(answer[1], curResult);
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (operatorCounts[i] > 0) {
                    operatorCounts[i]--; // 연산자 사용

                    int nextResult = 0;
                    switch (i) {
                        case 0: nextResult = curResult + numbers[depth + 1]; break;
                        case 1: nextResult = curResult - numbers[depth + 1]; break;
                        case 2: nextResult = curResult * numbers[depth + 1]; break;
                        case 3: nextResult = curResult / numbers[depth + 1]; break;
                    }

                    backtrack(depth + 1, nextResult);
                    operatorCounts[i]++; // 백트래킹 (복구)
                }
            }
        }
    }

 */