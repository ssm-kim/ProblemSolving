import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = 10;

        for (int tc = 0; tc < T; tc++) {
            int len = sc.nextInt();
            char[] str = sc.next().toCharArray();
            LinkedList<Character> stack = new LinkedList<>();
            int answer = 1;  // 초기 값은 1 (유효함)

            for (int i = 0; i < len; i++) {
                char cur_value = str[i];

                // 여는 괄호 처리
                if (cur_value == '(' || cur_value == '[' || cur_value == '{' || cur_value == '<') {
                    stack.offer(cur_value);
                }
                // 닫는 괄호 처리
                else if (cur_value == ')' || cur_value == ']' || cur_value == '}' || cur_value == '>') {
                    if (stack.isEmpty()) {
                        answer = 0; // 스택이 비어 있으면 0 처리;
                        break;
                    }
                    char last_value = stack.getLast();
                    boolean match = false;

                    // 여는 괄호와 닫는 괄호가 짝이 맞는지 확인
                    if (last_value == '(' && cur_value == ')') {
                        match = true;
                    } else if (last_value == '[' && cur_value == ']') {
                        match = true;
                    } else if (last_value == '<' && cur_value == '>') {
                        match = true;
                    } else if (last_value == '{' && cur_value == '}') {
                        match = true;
                    }

                    if (match) {
                        stack.removeLast();  // 짝이 맞으면 스택의 마지막 값 제거
                    } else {
                        answer = 0; // 짝이 맞지 않으면 0 처리;
                        break;
                    }
                }
            }
            // 모든 문자 처리 후 스택에 남았다면 0 처리
            if (!stack.isEmpty())
                answer = 0;
            System.out.println("#" + (tc + 1) + " " + answer);
        }
    }
}