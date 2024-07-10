package D3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1289 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int tc = 0; tc < T; tc++) {
            ArrayList<String> bit = new ArrayList<>(Arrays.asList(sc.nextLine().split("")));
            int cnt = 0;
            boolean state = false;  // 비트가 0인지 1인지 판단하는 상태값 변수
            for (int i = 0; i < bit.size(); i++) {
                if (bit.get(i).equals("1") && !state) {
                    state = true;
                    cnt += 1;
                } else if (bit.get(i).equals("0") && state){
                    state = false;
                    cnt += 1;
                }
            }
            System.out.printf("#%d %d\n", tc+1, cnt);
        }
    }
}
