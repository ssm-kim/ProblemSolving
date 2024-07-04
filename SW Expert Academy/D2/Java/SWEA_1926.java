package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1926 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        /* for문을 활용한 풀이
        String ans = "";
        for (int i = 1; i <= n; i++) {
            String str = Integer.toString(i);

            if (str.contains("3") || str.contains("6") || str.contains("9")) {
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '3' || str.charAt(j) == '6' || str.charAt(j) == '9') {
                        ans += "-";
                    }
                }
                ans += " ";
            } else {
                ans += str + " ";
            }

        }
        System.out.printf("%s ", ans);
        */

        /* String 라이브러리 replace 활용한 풀이
        for (int i = 1; i <= n; i++) {
            String str = Integer.toString(i);

            if (str.contains("3") || str.contains("6") || str.contains("9")) {
                str = str.replace("3", "-");
                str = str.replace("6", "-");
                str = str.replace("9", "-");

                str = str.replace("0", "-");
                str = str.replace("1", "-");
                str = str.replace("2", "-");
                str = str.replace("4", "-");
                str = str.replace("5", "-");
                str = str.replace("7", "-");
                str = str.replace("8", "-");
            }
            System.out.print(str + " ");
        }
        */
    }
}
