package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1959 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int [] a = new int[n];
            int [] b = new int[m];

            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            for (int i = 0; i < m; i++) b[i] = sc.nextInt();

            int max = 0;
            int sum;
            if (n < m) {
                for (int i = 0; i < m - n + 1; i++) {
                    sum = 0;
                    for (int j = 0; j < n; j++) {
                        sum += a[j] * b[i + j];
                    }
                    max = maxValueGet(sum, max);
                }
            } else if (n > m) {
                for (int i = 0; i < n - m + 1; i++) {
                    sum = 0;
                    for (int j = 0; j < m; j++) {
                        sum += b[j] * a[i + j];
                    }
                    max = maxValueGet(sum, max);
                }
            }
            System.out.printf("#%d %d\n", tc+1, max);
        }
    }

    public static int maxValueGet(int num1, int res) {
        if (num1 > res) {
            res = num1;
        }
        return res;
    }

}
