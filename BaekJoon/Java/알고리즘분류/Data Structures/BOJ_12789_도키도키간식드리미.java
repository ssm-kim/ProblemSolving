import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static LinkedList<Integer> wait = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            wait.add(Integer.parseInt(st.nextToken()));
        }

        int target = 1;
        while (!wait.isEmpty()) {
            int curNum = wait.pollFirst();

            System.out.println(wait);
            if (target == curNum) {
                target++;
            } else {
                stack.add(curNum);
            }

            for (int i = stack.size() - 1; i >= 0; i--) {
                if (target == stack.get(i)) {
                    stack.pop();
                    target++;
                } else {
                    break;
                }
            }
        }

        System.out.println(stack.isEmpty() ? "Nice" : "Sad");
    }
}