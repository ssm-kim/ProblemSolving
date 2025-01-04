import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer = 0;
    static int[] map;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new int[N];

        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        subset(0, 0);

        Integer[] arr = set.toArray(new Integer[0]);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 != arr[i]) {
                answer = i + 1;
                break;
            }  // 1부터 순서대로 있는지 체크
        }
        System.out.println(answer == 0 ? arr.length + 1 : answer);
    }

    static void subset(int depth, int sum) {
        if (depth == N) {
            if (sum != 0) {
                set.add(sum);
            }
            return;
        }
        subset(depth + 1, sum);
        subset(depth + 1, sum + map[depth]);
    }
}