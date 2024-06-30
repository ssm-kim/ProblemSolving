import java.util.Scanner;

public class SWEA_2019 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d ", (int) Math.pow(2, i));
        }
    }
}
