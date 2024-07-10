package D3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_5215 {
    static int[] score, cal;
    static int N, L, max;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        N = sc.nextInt();  // 재료 수
        L = sc.nextInt();  // 제한 칼로리

        for (int tc = 0; tc < T; tc++) {
            score = new int[N];  // 맛 점수
            cal = new int[N];    // 칼로리
            for (int i = 0; i < N; i++) {
                score[i] = sc.nextInt();
                cal[i] = sc.nextInt();
            }

            max = 0;  // 최대 점수
            DFS(0, 0, 0);
            System.out.printf("#%d %d\n", tc+1, max);
        }
    }

    static void DFS(int depth, int curScore, int calSum) {
        System.out.println(depth + " " + calSum);
        if (calSum > L)  return;  // 현재 칼로리 합이 제한 칼로리보다 크면 return;

        if (depth == N) {  // 최대 깊이가 도달하면 현재 점수와 비교 후 최댓값 갱신 후 return;
            max = Math.max(max, curScore);
            return;
        }

        DFS(depth + 1, curScore + score[depth], calSum + cal[depth]); // 재료 사용 O
        DFS(depth + 1, curScore, calSum); // 재료 사용 X
    }
}