package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1989 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            String input = sc.next();
            String [] words = input.split("");
            int len = words.length;

            int answer = 1;
            for (int i = 0; i < len/2; i++) {
                if (!words[i].equals(words[len-i-1])) {
                    answer = 0;
                    break;
                }
            }
            System.out.printf("#%d %d\n", tc+1, answer);
        }
    }
}
