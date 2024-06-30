package D1;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_2063 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];

        for (int i = 0; i<score.length; i++) {
            score[i] = sc.nextInt();
        }

        Arrays.sort(score);

        System.out.println(score[n/2]);
    }
}
