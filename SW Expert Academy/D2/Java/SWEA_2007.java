package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_2007 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        // 문자열 함수 substring 활용
        for (int tc = 0; tc < T; tc++) {
            String s = sc.next();
            int len = s.length();
            int res = 0;
            for (int j = 0; j < 10; j++) {
                String start = s.substring(0, j+1);
                String end = s.substring(j+1, (j+1) * 2);

                if (start.equals(end)) {
                    System.out.printf("#%d %d\n", tc+1, start.length());
                    break;
                }
            }
        }

    }
}
