package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1284 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int p = sc.nextInt(); // a회사 리터당 p원
            int q = sc.nextInt(); // b회사 기본 요금 q
            int r = sc.nextInt(); // r리터 이하 요금 q
            int s = sc.nextInt(); // 1리터당 요금
            int w = sc.nextInt(); // 한달 간 사용하는 수도의 양

            int a = p * w;
            int b;
            if (w <= r) {
                b = q;
            } else {
                b = q + (w-r) * s;
            }

            if (a < b) {
                System.out.printf("#%d %d\n", tc + 1, a);
            } else {
                System.out.printf("#%d %d\n", tc + 1, b);
            }

        }
    }
}
