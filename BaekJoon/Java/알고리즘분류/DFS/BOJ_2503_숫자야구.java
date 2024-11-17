import java.io.*;
import java.util.*;

public class Main {

    static final int len = 3;
    static int N, answer = 0;
    static String[] minHyuk;
    static int[] strike, ball;
    static boolean[] visited = new boolean[9];
    static HashSet<Integer> ballCheck = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minHyuk = new String[N];  // 민혁이가 질문한 세 자리 수
        strike = new int[N];      // 영수가 답한 스트라이크
        ball = new int[N];        // 볼의 개수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            minHyuk[i] =st.nextToken();
            strike[i] = Integer.parseInt(st.nextToken());
            ball[i] = Integer.parseInt(st.nextToken());
        }

        permutations(0, new int[len]);  // 1 ~ 9 숫자 중 3개를 선택하는 순열
        System.out.println(answer);
    }

    static void permutations(int depth, int[] selectCase) {
        if (depth == len) {
            if (isValid(selectCase)) answer++;
            return;
        }  // 이 경우가 유효한지? ( 여부 판단 )

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectCase[depth] = i + 1;
                permutations(depth + 1, selectCase);
                visited[i] = false;
            }
        }
    }

    static boolean isValid(int[] selectCase) {
        for (int i = 0; i < N; i++) {
            int curStrike = 0, curball = 0;
            ballCheck.clear();  // 매번 초기화

            // 비교할 숫자들을 Set에 저장
            for(int j = 0; j < len; j++) ballCheck.add(minHyuk[i].charAt(j) - '0');

            for (int j = 0; j < len; j++) {
                if (selectCase[j] == minHyuk[i].charAt(j) - '0') {
                    curStrike ++;
                }  // 스트라이크 확인
                
                else if (minHyuk[i].contains(String.valueOf(selectCase[j]))) {
                    curball++;
                }  // 스트라이크가 아닌 경우, 볼 확인
            }
            
            if (strike[i] != curStrike || ball[i] != curball) return false;
        }
        return true;
    }
}