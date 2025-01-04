import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] answer = new int[3];
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        System.out.println(Arrays.toString(answer));
    }

    static void dfs(int row, int col, int size) {
        if (checkNum(row, col, size)) {
            int num = map[row][col];
            if (num == -1) answer[0]++;
            else if (num == 0) answer[1]++;
            else answer[2]++;
            return;
        }  // 현재 영역이 모두 같은 숫자면 카운트 증가

        int newSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dfs(row + (newSize * i), col + (newSize * j), newSize);
            }
        }  // 다른 숫자가 있으면 9등분해서 재귀 호출
    }

    static boolean checkNum(int row, int col, int size) {
        int num = map[row][col];  // 첫 번째 숫자

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (map[i][j] != num) {
                    return false;
                }  // 다른 숫자 비교
            }
        }
        return true;
    }
}