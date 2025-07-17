import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] candidate = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            candidate[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());  // 총 감독관이 감시할 수 있는 응시자 수
        int c = Integer.parseInt(st.nextToken());  // 부 감독관이 감시할 수 있는 응시자 수

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int remaining = candidate[i];
            int totalSupervisors = 0;

            // 우선 총 감독관을 추가
            remaining -= b;
            totalSupervisors++;

            // 총 감독관 추가 했음에도 감시할 인원이 남아있다면 부 감독관 추가
            if (remaining > 0) {
                int subCount = remaining / c;

                if (remaining % c == 0) {
                    totalSupervisors += subCount;
                } else if (subCount >= 1) {
                    totalSupervisors += subCount + 1;
                } else {
                    totalSupervisors++;
                }
            }
            answer += totalSupervisors;
        }
        System.out.println(answer);
    }
}