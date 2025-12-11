import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] price = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            // 뒤에서부터 조회
            long answer = 0;
            int current = price[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                int next = price[i];

                if (current > next) {
                    answer += (current - next);  // 수익
                } else {
                    current = next;
                }
            }

            System.out.println(current + " " + answer);
        }
    }
}