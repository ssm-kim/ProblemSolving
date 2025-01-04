import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<int[]> stack = new LinkedList<>();  // 원본 스택
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 현재 탑과 이전 탑들 비교
            while (!stack.isEmpty() && stack.peek()[0] < height) {
                stack.poll();
            }

            if (!stack.isEmpty()) {
                answer[i] = stack.peek()[1];
            }

            // 현재 탑을 스택에 추가
            stack.push(new int[]{height, i + 1});
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
