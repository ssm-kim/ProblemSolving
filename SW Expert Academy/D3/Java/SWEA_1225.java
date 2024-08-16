import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc < 11; tc++) {
            Deque <Integer> queue = new LinkedList<>();
            StringBuilder st = new StringBuilder();

            tc = sc.nextInt();

            for (int i = 0; i < 8; i++) {
                queue.offer(sc.nextInt());
            }

            int decrease = 1;  // 감소시킬 값을 1로 초기화

            // 큐의 마지막 원소가 0이 될 때까지 반복
            while (queue.getLast() != 0) {
                int cur_num = queue.poll();  // 큐에서 첫 번째 원소를 제거

                // 감소시킬 값이 6이 되면 1로 초기화
                if (decrease % 6 == 0)
                    decrease = 1;

                cur_num -= decrease++;  // 현재 숫자에서 감소값을 빼고, 감소값을 증가시킴

                if (cur_num < 0) {      // 숫자가 0보다 작으면 0으로 설정
                    cur_num = 0;
                }
                queue.offer(cur_num);   // 감소된 숫자를 큐에 다시 추가
            }

            for (int val : queue) {
                st.append(val).append(" ");
            }
            System.out.println("#" + tc + " " + st);
        }
    }
}
