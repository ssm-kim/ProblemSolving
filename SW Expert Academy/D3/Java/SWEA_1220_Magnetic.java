import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int [][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }  // 입력 받기

            int answer = 0;
            for (int i = 0; i < N; i++) {
                int cnt = 0;
                boolean check = false;
                for (int j = 0; j < N; j++) {
                    if (map[j][i] == 1 && !check) {
                        check = true;
                    }  // N극
                    if (map[j][i] == 2 && check) {
                        check = false;
                        cnt++;
                    }  // S극, 교착 상태 시 +1
                }
                answer += cnt;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}