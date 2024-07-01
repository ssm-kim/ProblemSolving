package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_1945 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int[] num_lst = {2, 3, 5, 7, 11};
            ArrayList<Integer> res = new ArrayList<>();

            for (int i : num_lst) {
                int cnt = 0;
                while (n % i == 0) {
                    n /= i;
                    cnt += 1;
                }
                res.add(cnt);
            }

            System.out.printf("#%d ", (tc+1));
            for (Integer re : res) {
                System.out.printf("%d ", re);
            }
            System.out.println();
        }
    }
}