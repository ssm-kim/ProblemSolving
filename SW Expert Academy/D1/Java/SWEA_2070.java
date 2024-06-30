package D1;

import java.util.Scanner;

public class SWEA_2070 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            String res;
            if (num1 > num2) {
                res = ">";
            } else if (num1 < num2) {
                res = "<";
            } else {
                res = "=";
            }

            System.out.println("#" + (tc+1) + " " + res);
        }
    }
}
