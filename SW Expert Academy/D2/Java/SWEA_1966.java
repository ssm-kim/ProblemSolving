package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1966 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);
            System.out.printf("#%d ", (tc+1));
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}
