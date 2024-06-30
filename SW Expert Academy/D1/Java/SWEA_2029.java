import java.util.Scanner;

public class SWEA_2029 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.printf("#%d %d %d\n", tc+1, a/b, a%b);
        }
    }
}
