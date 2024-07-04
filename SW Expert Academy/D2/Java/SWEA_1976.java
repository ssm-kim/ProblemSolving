package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1976 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int fHour = sc.nextInt();
            int fMinute = sc.nextInt();
            int sHour = sc.nextInt();
            int sMinute = sc.nextInt();

            int hours = fHour + sHour;
            int minutes = fMinute + sMinute;

            if (minutes >= 60) {
                minutes -= 60;
                hours += 1;
            }

            if (hours > 12) {
                hours -= 12;
            }

            System.out.printf("#%d %d %d\n", tc+1, hours, minutes);
        }
    }
}
