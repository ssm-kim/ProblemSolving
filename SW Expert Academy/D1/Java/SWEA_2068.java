import java.util.Arrays;
import java.util.Scanner;

public class SWEA_2068 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] num_lst = new int[10];
        for (int tc = 0; tc < T; tc++) {

            for (int i = 0; i < 10; i++) {
                num_lst[i] = sc.nextInt();
            }
            Arrays.sort(num_lst);
            System.out.printf(String.format("#%d %d\n", (tc+1), num_lst[10-1]));
        }
    }
}
