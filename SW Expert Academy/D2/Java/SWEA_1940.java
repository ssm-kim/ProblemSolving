package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1940 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int distance = 0;  // 이동 거리
            int curSpeed = 0;  // 현재 속도
            for (int i = 0; i < n; i++) {
                int state = sc.nextInt();

                if (state == 1) {  // 가속
                    int accelerate = sc.nextInt();
                    curSpeed += accelerate;

                } else if (state == 2) {  // 감속
                    int decelerate = sc.nextInt();
                    if (curSpeed < decelerate) {  // 현재 속도보다 감속할 속도가 더 크면 속도는 0으로 초기화
                        curSpeed = 0;
                    } else {
                        curSpeed -= decelerate;
                    }

                } else {  // 현재 속도 유지

                }
                distance += curSpeed;
            }
            System.out.printf("#%d %d\n", tc+1, distance);
        }
    }
}
