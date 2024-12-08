import java.io.*;
import java.util.*;

public class Main {

    static final int TOTAL_PLAYERS = 9;

    static int N;               // 이닝 수
    static int[] seq;           // 타순
    static int[][] playerRes;   // 각 선수가 각 이닝에서 얻는 결과
    static boolean[] visited;   // 순열 생성 시 선수 선택 여부
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        playerRes = new int[N][TOTAL_PLAYERS];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < TOTAL_PLAYERS; j++) {
                playerRes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 순열로 타순 완전 탐색
        visited = new boolean[TOTAL_PLAYERS];
        seq = new int[TOTAL_PLAYERS];

        // 1번 선수(인덱스 0)는 4번 타자로 고정
        visited[0] = true;
        seq[3] = 0;

        perm(0);

        System.out.println(answer);
    }

    static void perm(int depth) {
        if (depth == TOTAL_PLAYERS) {
            int totalScore = simulate(seq);
            answer = Math.max(answer, totalScore);
            return;
        }

        if (depth == 3) {
            perm(depth + 1);
            return;
        }  // 4번 타자는 1번 선수로 고정

        for (int i = 0; i < TOTAL_PLAYERS; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[depth] = i;  // 타순
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }  // 순열을 이용해 가능한 모든 타순 생성

    static int simulate(int[] seq) {
        int playerIdx = 0;   // 현재 선수 번호
        int totalScore = 0;  // 누적 점수

        for (int inning = 0; inning < N; inning++) {
            int outCnt = 0;  // 아웃 카운트
            boolean[] bases = new boolean[3];  // 1루, 2루, 3루 주자 상태

            while (outCnt < 3) {
                int curPlayer = seq[playerIdx];
                int hit = playerRes[inning][curPlayer];
                if (hit == 0) {
                    outCnt++;
                }  // 아웃
                else {
                    totalScore += getScore(bases, hit);
                }  // 1루 2루 3루타 점수 계산
                playerIdx = (playerIdx + 1) % TOTAL_PLAYERS;  // 다음 타자 이동
            }
        }
        return totalScore;
    }

    static int getScore(boolean[] bases, int hit) {
        int curScore = 0;
        if (hit == 4) {
            for (int i = 0; i < 3; i++) {
                if (bases[i]) {
                    bases[i] = false;
                    curScore++;
                }
            }
            curScore++;  // 현재 타자 계산
        }  // 홈런
        else {
            for (int i = 2; i >= 0; i--) {
                if (bases[i]) {
                    if (i + hit >= 3) {
                        bases[i] = false;
                        curScore++;
                    }
                    else {
                        bases[i] = false;
                        bases[i + hit] = true;
                    }  // 진루 처리
                }
            }  // 3루 부터 이동

            bases[hit - 1] = true;  // 현재 주자 진루
        }  // 1루 2루 3루타 계산
        return curScore;
    }
}