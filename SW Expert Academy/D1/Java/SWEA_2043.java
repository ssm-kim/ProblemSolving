package D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_2043 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(Math.abs(p-k)+1);
    }
}
