package SBSSol.Lv05_문자열;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_9086 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            ArrayList<String> str = new ArrayList<>(Arrays.asList(br.readLine().split("")));
            System.out.println(str.get(0) + str.get(str.size()-1));
        }


    }
}
