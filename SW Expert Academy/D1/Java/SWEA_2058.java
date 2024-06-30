package D1;

import java.util.Scanner;

public class SWEA_2058 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = 0;

        while (n != 0) {
            total += (n % 10);
            n = n/10;
        }
        System.out.println(total);
    }

    // 문자열을 배열로 변경한 후 전체합
    public void Solution02() {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        String [] num_lst = n.split("");
        int total = 0;

        for (int i = 0; i < num_lst.length; i++) {
            total += Integer.parseInt(num_lst[i]);
        }
        System.out.println(total);
    }
}
