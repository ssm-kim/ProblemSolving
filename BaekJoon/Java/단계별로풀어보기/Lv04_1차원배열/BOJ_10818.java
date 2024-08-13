package SBSSol.Lv04_1차원배열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_10818 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> num_list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            num_list.add(Integer.parseInt(st.nextToken()));
        }

        System.out.printf("%d %d", Collections.min(num_list), Collections.max(num_list));

    }
}
