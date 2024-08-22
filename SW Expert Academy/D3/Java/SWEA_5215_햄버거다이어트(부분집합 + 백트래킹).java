import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    static int n, limit, answer;
    static int[] score, calories;


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            limit = sc.nextInt();

            score = new int[n];
            calories = new int[n];

            for (int i = 0; i < n; i++) {
                score[i] = sc.nextInt();
                calories[i] = sc.nextInt();
            }

            answer = 0;
            dfs(0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int depth, int sumScore, int sumCal) {
        // 현재 칼로리가 제한 칼로리를 넘어선다면 return
        if (sumCal > limit) return;

        // 최대 깊이 도달
        if (depth == n) {
            answer = Math.max(answer, sumScore);
            return;
        }

        // 현재 재료 사용 O
        dfs(depth + 1, sumScore + score[depth], sumCal + calories[depth]);
        // 현재 재료 사용 X
        dfs(depth + 1, sumScore, sumCal);
    }
}

