import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;  // 메모리

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[2];
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

//        for (int[] ints : arr) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();


        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int maxCnt = 0;
        int cur_end = 0;    // 현재 시작 시간
        for (int j = 0; j < arr.length; j++) {
            int next_start = arr[j][0];   // 다음 시작 시간
            int next_end = arr[j][1];     // 다음 끝나는 시간

            if (next_start >= cur_end ) {  // 다음 시작 시간과 현재 끝나는 시간
                cur_end = next_end;
                maxCnt++;
            }
        }
        System.out.println(maxCnt);
    }
}