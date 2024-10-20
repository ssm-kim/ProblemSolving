import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int T;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            char[] ps = br.readLine().toCharArray();
            stack = new Stack<>();
            for (char p : ps) {
                if (p == '(') {
                    stack.add(p);
                } else if (!stack.isEmpty()){
                    char last = stack.peek();
                    if (last == '(' && p == ')') {
                        stack.pop();
                    }
                } else {
                    stack.add(p);
                }
            }
            System.out.println(stack.isEmpty() ? "YES" : "NO");
        }
    }
}