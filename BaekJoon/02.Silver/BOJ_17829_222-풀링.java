import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
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
        System.out.println(dfs(0, 0, N));
    }

    static int dfs(int x, int y, int size) {
        if (size == 2) {
            System.out.println(x + " " + y);
            int[] temp = new int[4];

            temp[0] = map[x][y];
            temp[1] = map[x + 1][y];
            temp[2] = map[x][y + 1];
            temp[3] = map[x + 1][y + 1];

            Arrays.sort(temp);

            return temp[2];
        }  // 기저 조건 2 * 2 크기 도달했을 때

        int nextSize = size / 2;
        int[] nextMap = new int[4];

        nextMap[0] = dfs(x, y, nextSize);
        nextMap[1] = dfs(x + nextSize, y, nextSize);
        nextMap[2] = dfs(x, y + nextSize, nextSize);
        nextMap[3] = dfs(x + nextSize, y + nextSize, nextSize);  // 4개의 부분으로 분할 후 재귀 호출

        Arrays.sort(nextMap);

        return nextMap[2];
    }
}