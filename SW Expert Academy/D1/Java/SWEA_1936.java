package D1;

import java.util.Scanner;

public class SWEA_1936 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        String res;
        if (a.equals("1") && b.equals("3") || a.equals("2") && b.equals("1") || a.equals("3") && b.equals("2")) {
            res = "A";
        } else {
            res = "B";
        }
        System.out.println(res);
    }
}
