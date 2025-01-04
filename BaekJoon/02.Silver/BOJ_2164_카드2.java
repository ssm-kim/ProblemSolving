import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        while (queue.size() != 1) {
            queue.poll();
            queue.offer(queue.poll());
        }
        System.out.println(queue.peek());
    }
}