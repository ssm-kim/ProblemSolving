package SBSSol.Lv05_문자열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_2908 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] s = br.readLine().split(" ");
        int max = 0;
        for (int i = 0; i < s.length; i++) {
            List<String> str = Arrays.asList(s[i].split(""));
            Collections.reverse(str);
            String tmp = "";
            for (int j = 0; j < str.size(); j++) {
                tmp += str.get(j);
            }

            if (Integer.parseInt(tmp) > max) {
                max = Integer.parseInt(tmp);
            }
        }
        System.out.println(max);
    }
}
