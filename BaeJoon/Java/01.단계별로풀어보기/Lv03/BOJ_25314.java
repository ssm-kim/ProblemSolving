package SBSSol.Lv03;

import java.io.*;

public class BOJ_25314 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n / 4; i++) {
            System.out.print("long ");
        }
        System.out.print("int");
    }
}
