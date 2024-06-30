import java.util.Scanner;

public class SWEA_2056 {

    public static int arr[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            String str = sc.next();
            String year = str.substring(0, 4);
            int month = Integer.parseInt(str.substring(4, 6));
            int day = Integer.parseInt(str.substring(6, 8));

            if ((0 < month && month < 13) && (day <= arr[month - 1])) {
                System.out.println("#" + (tc + 1) + " " + year + "/" + String.format("%02d", month) + "/" + String.format("%02d", day));
            } else {
                System.out.println("#" + (tc + 1) + " " + -1);
            }
        }
    }
}
