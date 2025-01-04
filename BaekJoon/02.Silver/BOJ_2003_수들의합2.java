import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N, M, answer;
    static int[] nums;
    static int[] prefix;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        prefix = new int[N + 1];
        answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }  // 누적 합 구하기

        for (int i = 1; i <= N; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int range = prefix[j] - prefix[i];
                if (range == M) {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}