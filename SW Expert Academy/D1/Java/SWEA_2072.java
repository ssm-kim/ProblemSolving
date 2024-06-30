import java.util.Scanner;

public class SWEA_2072 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int[][] num_lst = new int[tc][10];

        for (int i = 0; i < tc; i++) {
            for (int j = 0; j < num_lst[i].length; j++) {
                num_lst[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < tc; i++) {
            int total = 0;
            for (int j = 0; j < num_lst[i].length; j++) {
                if (num_lst[i][j] % 2 != 0) {
                    total += num_lst[i][j];
                }
            }
            System.out.println("#" + (i+1) + " " + total);
        }
    }
}
