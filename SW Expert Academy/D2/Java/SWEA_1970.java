package D2;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;

public class SWEA_1970 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int[] change = {50000, 10000, 5000, 1000, 500, 100, 50, 10};  // 거스름돈
            System.out.printf("#%d\n", tc+1);
            for (int i : change) {
                int kindMoney = 0;
                if (n / i > 0) {
                    kindMoney = n / i;
                    n = n % i;  // 나머지 값
                }
                System.out.printf("%d ", kindMoney);
            }
            System.out.println();
        }
    }
}
