package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 파리 퇴치 3
public class SWEA_12712 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int [][] board = new int [n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            ArrayList<Integer> dx = new ArrayList<>(Arrays.asList(-1, 0, 1, 0, -1, 1, 1, -1));  // + 형태의 스프레이, x 형태의 스프레이
            ArrayList<Integer> dy = new ArrayList<>(Arrays.asList(0, 1, 0, -1, 1, 1, -1, -1));  // 방향 벡터 활용 (0 ~ 3 -> '+', 4 ~ 7 -> 'x')
            int maxCnt = 0;
            int px = 0, py = 0; // '+' 인덱스
            int nx = 0, ny = 0; // 'x' 인덱스

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cnt1 = board[i][j];  // '+' 모양 분사, 중앙값 설정
                    int cnt2 = board[i][j];  // 'x' 모양 분사, 중앙값 설정
                    for (int k = 0; k < 4; k++) {
                        for (int l = 1; l < m; l++) {
                            px += i + (dx.get(k) * l);      // '+' 방향 m만큼 이동
                            py += j + (dy.get(k) * l);
                            nx += i + (dx.get(k + 4) * l);  // 'x' 방향 m만큼 이동
                            ny += j + (dy.get(k + 4) * l);

                            if (px >= 0 && py >= 0 && px < n && py < n) {  // System.out.println(nx + "  " + ny);
                                cnt1 += board[px][py];
                            }
                            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                cnt2 += board[nx][ny];
                            }
                            px -= i + (dx.get(k) * l);  // 인덱스 복귀
                            py -= j + (dy.get(k) * l);
                            nx -= i + (dx.get(k + 4) * l);
                            ny -= j + (dy.get(k + 4) * l);
                        }
                    }
                    maxCnt = Math.max(maxCnt, Math.max(cnt1, cnt2));  // 최대값 갱신
                }
            }
            System.out.printf("#%d %d\n", tc+1, maxCnt);
        }
    }
}