import java.io.*;
import java.util.*;

public class Main {

    static int l, c;
    static char[] kinds;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        kinds = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            kinds[i] = st.nextToken().charAt(0);
        }

        // 정렬 후 조합 탐색
        Arrays.sort(kinds);
        combinations(0, 0, new StringBuilder());

    }

    static void combinations(int depth, int start, StringBuilder password) {
        if (depth == l) {
            // 모음/자음 개수 검증
            String s = password.toString();
            int ja = 0, mo = 0;
            for (int i = 0; i < s.length(); i++) {
                char target = s.charAt(i);
                if (target == 'a' || target == 'e' ||
                    target == 'i' || target == 'o' || target == 'u') mo++;
                else ja++;
            }
            if (ja >= 2 && mo >= 1) System.out.println(s);
            return;
        }

        // i = start부터 시작 → 오름차순 조합만 생성
        for (int i = start; i < c; i++) {
            password.append(kinds[i]);
            combinations(depth + 1, i + 1, password);
            password.deleteCharAt(password.length() - 1);  // 백트래킹
        }
    }
}

