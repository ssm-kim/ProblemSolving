import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();
            int cnt = 0;
            int[][] arr = new int[100][100];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            /**
             * 1 : N극 성질 (아래로)
             * 2 : S극 성질 (위로)
             */

            for (int i = 0; i < n; i++) {
                boolean flag = false;  // 열 검사 이후 초기화
                for (int j = 0; j < n; j++) {
                    if (!flag && arr[j][i] == 1) {
                        flag = true;
                    }
                    else if (flag && arr[j][i] == 2) {  // 교착 상태 시 +1
                        cnt++;
                        flag = false;
                    }
                }
            }
            System.out.println("#" + tc + " " + cnt);
        }
    }
}
