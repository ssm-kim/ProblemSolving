import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();  // 메모리
        int[] heights = new int[n];
        int[] res = new int[n];

        // 탑의 높이 저장
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 각 탑에 대해 수신 여부 확인
        for (int i = 0; i < n; i++) {

            int curHeight = heights[i];  // 현재 탑의 높이

            // 스택이 비어있지 않고 현재 탑의 높이보다 낮은 탑이 스택에 있는 경우
            while (!stack.isEmpty() && heights[stack.peek()] < curHeight) {
                stack.pop();
            }

            // 스택이 비어있지 않다면 현재 탑이 수신된 탑
            if (!stack.isEmpty()) {
                res[i] = stack.peek() + 1;
            } else {
                res[i] = 0;
            }

            // 현재 탑의 인덱스를 스택에 추가
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int x : res) {
            sb.append(x).append(" ");
        }

        System.out.println(sb.toString().trim());  // 양쪽 공백 제거
    }
}