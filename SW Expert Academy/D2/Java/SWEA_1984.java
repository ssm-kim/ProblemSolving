package D2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class SWEA_1984 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // Stack 객체, max, min 함수를 이용한 풀이
        for (int tc = 0; tc < T; tc++) {
            String [] str = br.readLine().split(" ");
            Stack<Integer> num_list = new Stack<>();
            for (int i = 0; i < str.length; i++) {
                num_list.add(Integer.parseInt(str[i]));
            }

            num_list.remove(num_list.indexOf(Collections.max(num_list)));
            num_list.remove(num_list.indexOf(Collections.min(num_list)));

            int sum = 0;
            for (int i : num_list) {
                sum += i;
            }

            double avg = (sum + 0.0) / (num_list.size());

            System.out.printf("#%d %.0f\n", tc+1, avg);

        }

//        정렬을 이용한 풀이
//        for (int tc = 0; tc < T; tc++) {
//            String [] str = br.readLine().split(" ");
//            ArrayList<Integer> num_list = new ArrayList<>();
//            for (int i = 0; i < str.length; i++) {
//                num_list.add(Integer.parseInt(str[i]));
//            }
//            Collections.sort(num_list);  // 정렬 후 최소값, 최대값 제거
//
//            int sum = 0;
//            for (int i = 1; i < num_list.size() - 1; i++) {
//                sum += num_list.get(i);
//            }
//            double avg = (double)sum/(num_list.size()-2);
//
//            System.out.printf("#%d %.0f\n", tc+1, avg);
//        }

    }
}
