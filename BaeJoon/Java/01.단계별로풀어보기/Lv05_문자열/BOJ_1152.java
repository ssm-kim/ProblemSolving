package SBSSol.Lv05_문자열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1152 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] s = br.readLine().split(" ");

        System.out.println(s.length);
    }
}
