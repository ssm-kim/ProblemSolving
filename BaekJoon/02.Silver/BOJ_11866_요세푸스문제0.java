import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        StringBuilder sb = new StringBuilder("<");
        int idx = 0;
        while (!queue.isEmpty()) {
            idx = (idx + K - 1) % queue.size();  // K번째 위치 추적
            if (queue.size() == 1) {
                sb.append(queue.remove(idx)).append(">");
                break;
            }
            sb.append(queue.remove(idx)).append(", ");
        }
        System.out.println(sb);
    }
}