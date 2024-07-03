package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1954 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int [] dx = {0, 1, 0, -1};  // x축 고정 방향
        int [] dy = {1, 0, -1, 0};  // y축 고정 방향

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int [][] snailNum = new int[n][n];
            int num = 1;
            int cx = 0, cy = 0, dir = 0; // dir -> dx, dy 방향 인덱스

            while (num <= n*n) {

                if (snailNum[cx][cy] == 0) {
                    snailNum[cx][cy] = num;
                }
                // System.out.println(snailNum[cx][cy]);
                cx += dx[dir];  // 현재 x, y 인덱스 번호
                cy += dy[dir];

                // 인덱스 범위를 벗어나거나 값이 0이 아니라면 dir 변경
                if (cx < 0 || cx >= n || cy < 0 || cy >= n || snailNum[cx][cy] != 0) {
                    // 복귀
                    cx -= dx[dir];
                    cy -= dy[dir];

                    dir = (dir + 1) % 4;
                    // 이동
                    cx += dx[dir];
                    cy += dy[dir];
                }

                num += 1;
            }

            System.out.printf("#%d\n", tc+1);
            for (int[] rows : snailNum) {
                for (int val : rows) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
        }
    }
}
