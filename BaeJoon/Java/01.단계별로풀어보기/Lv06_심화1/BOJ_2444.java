package SBSSol.Lv06_심화1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class BOJ_2444 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
