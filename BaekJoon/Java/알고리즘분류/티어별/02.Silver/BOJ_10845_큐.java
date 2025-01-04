import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayDeque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] cmd = br.readLine().split(" ");

            if (cmd.length == 2) {
                queue.offer(Integer.parseInt(cmd[1]));
            }
            else {
                switch (cmd[0]) {
                    case "pop":
                        System.out.println(queue.isEmpty() ? -1 : queue.poll());
                        break;
                    case "size":
                        System.out.println(queue.size());
                        break;
                    case "empty":
                        System.out.println(queue.isEmpty() ? 1 : 0);
                        break;
                    case "front":
                        System.out.println(queue.isEmpty() ? -1 : queue.peek());
                        break;
                    case "back":
                        System.out.println(queue.isEmpty() ? -1 : queue.getLast());
                        break;
                }
            }
        }
    }
}