import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] playerRes;    // 각 선수가 각 이닝에서 얻는 결과
    static int[] seq;            // 타순
    static boolean[] visited;    // 선수 방문 여부
    static int maxScore = 0;     // 최대 점수


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        playerRes = new int[N][9];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                playerRes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        seq  = new int[9];
        visited = new boolean[9];  // 선수 번호(0 ~ 8)에 대한 방문 체크

        // 1번 선수(인덱스 0)는 4번 타자로 고정
        seq[3] = 0;
        visited[0] = true;

        perm(0);

        System.out.println(maxScore);
    }

    static void perm(int depth) {
        if (depth == 9) {
            int curScore = simulate();
            maxScore = Math.max(maxScore, curScore);
            return;
        }

        if (depth == 3) {
            perm(depth + 1);
            return;
        }  // 4번 타자는 이미 1번 선수로 고정

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[depth] = i;
                perm(depth + 1);
                visited[i] = false;
            }
        }  // 나머지 선수들 타순 정하기
    }

    static int simulate() {
        int score = 0;      // 총 점수
        int playerIdx = 0;  // 현재 타자의 순서

        // N 이닝 동안 게임 진행
        for (int inning = 0; inning < N; inning++) {
            int outCnt = 0;
            boolean[] bases = new boolean[3];  // 1루 2루 3루

            while (outCnt < 3) {
                int curPlayer = seq[playerIdx];  // 현재 타자 번호
                int hit = playerRes[inning][curPlayer];  // 현재 타자의 결과

                if (hit == 0) {
                    outCnt++;
                }  // 아웃 카운트 +1
                else {
                    score += getScore(bases, hit);  // 점수 계산
                }
                playerIdx = (playerIdx + 1) % 9;  // 다음 타자
            }
        }
        return score;
    }

    // bases -> 진루 상황, hit 타자 결과
    static int getScore(boolean[] bases, int hit) {
        int score = 0;

        if (hit == 4) {
            for (int i = 0; i < 3; i++) {
                if (bases[i]) {
                    score++;
                    bases[i] = false;
                }
            }  // 1루 2루 3루 주자 처리
            score++;  // 타자 본인
        }  // 홈런인 경우
        else {
            for (int i = 2; i >= 0; i--) {
                if (bases[i]) {
                    if (i + hit >= 3) {
                        score++;
                        bases[i] = false;
                    }  // 홈인
                    else {
                        bases[i + hit] = true;
                        bases[i] = false;
                    }  // 진루
                }
            }  // 3루부터 진루 처리

            // 타자주자 진루
            bases[hit - 1] = true;

        }  // 안타, 2루타, 3루타인 경우

        return score;
    }
}