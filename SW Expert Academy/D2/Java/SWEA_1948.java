package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1948 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int tc = 0; tc < T; tc++) {
            int startMonth = sc.nextInt();
            int startDay = sc.nextInt();
            int endMonth = sc.nextInt();
            int endDay = sc.nextInt();

            int days = 0;

            for (int i = startMonth; i <= endMonth; i++) {
                if (i == startMonth) {
                    days += daysInMonth[startMonth] - startDay + 1;
                } else if (i == endMonth) {  // 마지막 달에는 일수만 더하면 됨.
                    days += endDay;
                } else {
                    days += daysInMonth[i];
                }
            }
            System.out.printf("#%d %d\n", tc+1, days);
        }
    }
}
