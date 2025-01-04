import java.io.*;
import java.util.*;

public class Main {

    static int N, answer = 0;
    static int[][] locations;
    static int[][] map = new int[100][100];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 색종이의 수
        locations = new int[N][2];  //

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            // row, col  >  좌하단
            for (int j = row; j < row + 10; j++) {
                for (int k = col; k < col + 10; k++) {
                    if (map[j][k] == 0) {
                        map[j][k] = 1;
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}