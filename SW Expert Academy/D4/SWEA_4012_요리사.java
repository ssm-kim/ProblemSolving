import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    static int N;
    static int[][] map;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int[] ints : map) {
                System.out.println(Arrays.toString(ints));
            }

            arr = new ArrayList[N/2];
            for (int i = 0; i < N/2; i++) {
                arr[i] = new ArrayList<>();
            }
            dfs(0, 0);

            break;
        }
    }

    static void dfs(int depth, int start) {
        if (depth == N) {
            for (ArrayList<Integer> integers : arr) {
                System.out.println(integers);
            }
            System.out.println();
            return;
        }  // 기저조건 : 두가지 식재료를 골랐을 때

        for (int i = start; i < N; i++) {
            arr[depth].add(i);
            dfs(depth + 1, i + 1);
            arr[depth].removeLast();
        }
    }
}
