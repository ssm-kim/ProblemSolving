package D1;

import java.util.Scanner;

public class SWEA_1545 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = n; i >= 0; i--) {
            System.out.printf(String.format("%d ", i));
        }
    }
}
