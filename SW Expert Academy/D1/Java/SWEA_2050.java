package D1;

import java.util.Scanner;

public class SWEA_2050 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();

        for (int i = 0; i < line.length(); i++) {
            System.out.printf("%d ", line.charAt(i)-64);
        }
    }
}
