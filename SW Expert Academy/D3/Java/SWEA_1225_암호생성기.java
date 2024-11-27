import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static final int LEN = 8;
    static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            tc = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.clear();

            for (int i = 0; i < LEN; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }  // 입력값

            int cycle = 1;  // 1 cycle : 1 2 3 4 5
            while (queue.getLast() != 0) {
                if (cycle == 6) cycle = 1;

                int select = queue.removeFirst() - cycle;
                if (select < 0) {
                    queue.offer(0);
                    continue;
                }  // 0보다 작아지는 경우 0 유지

                queue.offer(select);
                cycle++;

            }  // 마지막 숫자가 0이면 종료

            StringBuilder sb = new StringBuilder();
            for (int num : queue) sb.append(num).append(" ");

            System.out.println("#"  + tc + " " + sb);
        }
    }
}