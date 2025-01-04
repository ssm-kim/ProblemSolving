import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int N;
    static int[] S;  // 내구도
    static int[] W;  // 무게

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];
        W = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());  // 내구도
            W[i] = Integer.parseInt(st.nextToken());  // 무게
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int curIdx, int cnt) {
        if (curIdx == N) {
            answer = Math.max(answer, cnt);
            return;
        }

        if (S[curIdx] <= 0) {
            dfs(curIdx + 1, cnt);
            return;
        }  // 현재 계란이 깨져 있다면 다음 계란 이동

        boolean hit = false;  // 칠 수 있는 계란 확인
        for (int i = 0; i < N; i++) {
            if (curIdx == i) continue;  // 현재 계란과 같다면  pass
            if (S[i] <= 0) continue;    // 이미 깨진 계란 이면 Pass

            // 서로 계란 부딪히기
            int brokenEgg = 0;
            S[curIdx] -= W[i];
            S[i] -= W[curIdx];
            hit = true;  // 계란을 하나라도 쳤다면

            // 부딪힌 후 깨진 계란 카운트
            if (S[curIdx] <= 0) brokenEgg++;
            if (S[i] <= 0) brokenEgg++;

            dfs(curIdx + 1, cnt + brokenEgg);

            // 백트래킹 (복구)
            S[curIdx] += W[i];
            S[i] += W[curIdx];
        }  // 현재 계란이 깨져 있지 않다면 다음 계란과 부딪히기

        if (!hit) dfs(curIdx + 1, cnt);  // 어떤 계란도 치지 못한 경우

    }
}