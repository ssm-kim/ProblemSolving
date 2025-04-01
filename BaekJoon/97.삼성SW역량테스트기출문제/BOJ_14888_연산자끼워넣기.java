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
    static int[] selected;
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
        selected = new int[N - 1];
        permutations(0);
        System.out.println(max + "\n" + min);
    }

    static void permutations(int depth) {
        if (depth == N - 1) {

            int res = calculate();

            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                selected[depth] = i;
                permutations(depth + 1);
                operator[i]++;
            }
        }
    }

    static int calculate() {
        int res = numbers[0];  // 첫 번째 숫자로 초기화

        for (int i = 0; i < N - 1; i++) {
            switch (selected[i]) {
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