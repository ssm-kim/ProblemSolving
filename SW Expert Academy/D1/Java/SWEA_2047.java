package D1;

import java.util.Scanner;

public class SWEA_2047 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        // System.out.println(n.toUpperCase());
        char [] ch = n.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            System.out.print(Character.toUpperCase(ch[i]));
        }
    }
}

