import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int answer = 0;
        int count = 0;  // 연속된 IOI 패턴 개수

        for (int i = 1; i < m - 1; i++) {
            // IOI 패턴 발견
            if (s.charAt(i - 1) == 'I' && s.charAt(i) == 'O' && s.charAt(i + 1) == 'I') {
                count++;

                // count가 n에 도달하면 P_n 하나 발견
                if (count == n) {
                    answer++;
                    count--;  // 다음 패턴을 위해서
                }

                i++;  // O 다음의 I로 건너뜀
            }
            else {
                count = 0;  // 패턴 끊기면 초기화
            }
        }

        System.out.println(answer);
    }
}