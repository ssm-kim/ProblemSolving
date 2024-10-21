import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static Map<Integer, Integer> aMap = new LinkedHashMap<>();
    static int[] bMap;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aMap.put(Integer.parseInt(st.nextToken()), 1);
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        bMap = new int[M];
        for (int i = 0; i < M; i++) {
            bMap[i] = Integer.parseInt(st.nextToken());
        }

        for (int num : bMap) {
            if (aMap.containsKey(num)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}