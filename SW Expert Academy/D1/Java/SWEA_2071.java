import java.util.Scanner;

public class SWEA_2071 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += sc.nextInt();
            }

            double avg = (double) sum/10;
            System.out.println("#" + (tc+1) + " " + String.format("%.0f", avg));
        }
    }
}
