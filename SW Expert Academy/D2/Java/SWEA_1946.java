package D2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1946 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList <String> str = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String alpha = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                String strTemp = "";

                for (int j = 0; j < num; j++) {
                    strTemp = strTemp.concat(alpha);
                }

                str.addAll(Arrays.asList(strTemp.split("")));  // 문자열을 하나씩 나누어서 ArrayList 삽입
            }
            str.add(0, "");  // 첫번째 인덱스는 사용안함.

            System.out.printf("#%d\n", tc+1);
            for (int i = 1; i < str.size(); i++) {
                if (i % 10 == 0) {
                    System.out.println(str.get(i));
                    continue;
                }
                System.out.printf(str.get(i));
            }
            System.out.println();
        }
    }
}
