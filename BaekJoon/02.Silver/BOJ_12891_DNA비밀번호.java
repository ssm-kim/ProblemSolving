import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int S, P, answer = 0;
    static char[] map;
    static int[] fixInclude = new int[4];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());  // DNA 문자열 길이
        P = Integer.parseInt(st.nextToken());  // 비밀번호로 사용할 부분 문자열 길이
        map = br.readLine().toCharArray();     // 임의로 만든 DNA 문자열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            fixInclude[i] = Integer.parseInt(st.nextToken());
        }  // 부분 문자열에 포함 되어야 할 A C G T 갯수

        HashMap<Character, Integer> curInclude = new HashMap<>() {{
            put('A', 0);
            put('C', 0);
            put('G', 0);
            put('T', 0);
        }};

        for (int i = 0; i < P; i++) {
            switch (map[i]) {
                case 'A': curInclude.put('A', curInclude.get('A') + 1);
                    break;
                case 'C': curInclude.put('C', curInclude.get('C') + 1);
                    break;
                case 'G': curInclude.put('G', curInclude.get('G') + 1);
                    break;
                case 'T': curInclude.put('T', curInclude.get('T') + 1);
                    break;
            }
        }  // 초기값 세팅

        boolean search = false;
        for (int i = 0; i < S - P + 1; i++) {
            if (search) {
                char rm = map[P + i - 1];
                curInclude.put(rm, curInclude.get(rm) + 1);
            }  // 다음 값 증가

            compareAlpha(curInclude, i);

            if (!search) search = true;
        }
        System.out.println(answer);
    }

    static void compareAlpha(HashMap<Character, Integer> curInclude, int start) {

        if ( fixInclude[0] <= curInclude.get('A') &&
                fixInclude[1] <= curInclude.get('C') &&
                fixInclude[2] <= curInclude.get('G') &&
                fixInclude[3] <= curInclude.get('T') ) {
            answer++;
        }  // 조건 체크

        char rm = map[start];
        switch (rm) {
            case 'A': curInclude.put('A', curInclude.get('A') - 1);
                break;
            case 'C': curInclude.put('C', curInclude.get('C') - 1);
                break;
            case 'G': curInclude.put('G', curInclude.get('G') - 1);
                break;
            case 'T': curInclude.put('T', curInclude.get('T') - 1);
                break;
        }  // 이전 값 감소
    }
}
