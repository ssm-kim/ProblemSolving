import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int answer;
    static int D, W, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());  // 두께
            W = Integer.parseInt(st.nextToken());  // 가로 크기
            K = Integer.parseInt(st.nextToken());  // 합격 기준

            answer = Integer.MAX_VALUE;
            map = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 0 -> A, 1 -> B
            dfs(0, 0, new int[W]);

            System.out.println("#" + tc + " " + (answer == Integer.MAX_VALUE ? 0 : answer));
        }
    }

    static void dfs(int row, int cnt, int[] temp) {
        if (cnt >= answer) return;  // 현재 약품 투입 값이 answer 이상 -> 가지 치기

        if (row == D) {
            if (check()) {
                answer = Math.min(cnt, answer);
            }  // 성능 검사 로직
            return;
        }

        // A, B 약품 투입 X
        dfs(row + 1, cnt, temp);

        // A 약품(0) 투입
        temp = map[row].clone();  // 이전 상태 저장
        Arrays.fill(map[row], 0);
        dfs(row + 1, cnt + 1, temp);
        map[row] = temp;  // 백트래킹

        // B 약품(1) 투입
        temp = map[row].clone();
        Arrays.fill(map[row], 1);
        dfs(row + 1, cnt + 1, temp);
        map[row] = temp;
    }

    static boolean check() {
        for (int i = 0; i < W; i++) {
            int maxCnt = Integer.MIN_VALUE;  // 연속된 최대 개수
            int drug = 0;           // 현재 연속 카운트
            boolean state = false;  // false: B약품, true: A약품 상태

            for (int j = 0; j < D; j++) {
                if (map[j][i] == 0) {
                    if (!state) {
                        maxCnt = Math.max(maxCnt, drug);
                        drug = 0;
                    }  // 이전과 다르면 최댓값 갱신 후 초기화
                    drug++;
                    state = true;
                }  // A약품(0) 만났을 때

                if (map[j][i] == 1) {
                    if (state) {
                        maxCnt = Math.max(maxCnt, drug);
                        drug = 0;
                    }
                    drug++;
                    state = false;
                }   // B약품(1) 만났을 때

                if (j == D-1) {
                    maxCnt = Math.max(maxCnt, drug);
                }  // 마지막 행에서 갱신

            }
            if (maxCnt < K) return false;  // 연속 K개 미만  ->  실패
        }
        return true;
    }
}