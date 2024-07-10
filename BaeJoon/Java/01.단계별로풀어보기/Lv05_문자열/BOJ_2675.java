package SBSSol.Lv05_문자열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2675 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            ArrayList<String> str = new ArrayList<>(Arrays.asList(st.nextToken().split("")));

            for (int i = 0; i < str.size(); i++) {
                for (int j = 0; j < r; j++) {
                    System.out.print(str.get(i));
                }
            }
            System.out.println();
        }
    }
}
