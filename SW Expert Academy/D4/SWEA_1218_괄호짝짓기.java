import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static LinkedList<Character> stack;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            stack = new LinkedList<>();
            char[] str = br.readLine().toCharArray();

            for (int i = 0; i < str.length; i++) {
                char state = str[i];

                if (state == '{' || state == '[' || state == '(' || state == '<') stack.add(state);

                switch (state) {
                    case '}':
                        check(state, '{');
                        break;
                    case ']':
                        check(state, '[');
                        break;
                    case ')':
                        check(state, '(');
                        break;
                    case '>':
                        check(state, '<');
                        break;
                }
            }
            System.out.println(stack.isEmpty() ? "#" + tc + " 1" : "#" + tc + " 0");
        }
    }

    private static void check(char state, char compare) {
        if (!stack.isEmpty() && stack.peekLast() == compare) {
            stack.pollLast();
        } else {
            stack.add(state);
        }
    }
}