import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, sum = 0;
        answer = 0;

        while (true) {
            if (sum >= M) {
                sum -= arr[start++];
            } else if (end == N) {
                break;
            } else {
                sum += arr[end++];
            }

            if (sum == M) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

//(재귀 + 완탐)
//public class Main {
//
//    static int N, M, answer;
//    static int[] arr;
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine());
//        arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        answer = 0;
//        for (int i = 0; i < N; i++) {
//            findSum(i, 0);
//        }
//        System.out.println(answer);
//    }
//
//    static void findSum(int depth, int sum) {
//        if (depth == N) {
//            return;
//        }
//
//        sum += arr[depth];
//
//        if (sum == M) {
//            answer++;
//        }
//
//        if (sum < M) {
//            findSum(depth + 1, sum);
//        }
//    }
//}