import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static LinkedList<Integer> queue = new LinkedList<>();;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N ; i++) {
            queue.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int seq = 0;
        while (!queue.isEmpty()) {
            seq++;
            if (seq == K) {
                seq = 0;
                sb.append(queue.size() == 1 ? queue.pollFirst() + ">" : queue.pollFirst() + ", ");
                continue;
            }
            queue.offer(queue.pollFirst());
        }
        System.out.println(sb);
    }
}