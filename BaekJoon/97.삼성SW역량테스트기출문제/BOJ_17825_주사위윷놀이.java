import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int maxScore = 0;

    // 가장 일반적인 정답 코드 구조 - 33개 칸으로 매핑
    static int[] score = {
        0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, // 0~20: 일반경로
        0,          // 21: 도착점
        13, 16, 19, // 22~24: 10→특수경로
        25,         // 25: 합류점
        22, 24,     // 26~27: 20→특수경로
        28, 27, 26, // 28~30: 30→특수경로
        30, 35      // 31~32: 25 이후
    };

    // 각 칸의 다음 칸 번호
    static int[] next = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, // 0~20: 일반경로
        21,         // 21: 도착점
        23, 24, 25, // 22~24: 10→특수경로
        31,         // 25: 합류점→30
        27, 25,     // 26~27: 20→특수경로
        29, 30, 25, // 28~30: 30→특수경로
        32, 20      // 31~32: 25 이후→40
    };

    static int[] dice = new int[10];
    static int[] horse = new int[4];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(maxScore);
    }

    static void dfs(int turn, int totalScore) {
        if (turn == 10) {
            maxScore = Math.max(maxScore, totalScore);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (horse[i] == 21) continue; // 이미 도착한 말

            int pos = move(horse[i], dice[turn]);
            if (pos == -1) continue; // 이동 불가 (다른 말과 겹침)

            int prev = horse[i];
            horse[i] = pos;
            dfs(turn + 1, totalScore + score[pos]);
            horse[i] = prev; // 백트래킹
        }
    }

    static int move(int pos, int diceValue) {
        // 분기점 처리: 파란색 칸에서 이동 시작하면 특수경로
        if (pos == 5) pos = 22;       // 10점 칸 → 13점 특수경로
        else if (pos == 10) pos = 26; // 20점 칸 → 22점 특수경로
        else if (pos == 15) pos = 28; // 30점 칸 → 28점 특수경로
        else {
            pos = next[pos]; // 일반 이동
        }

        // 나머지 주사위 수만큼 이동
        for (int i = 1; i < diceValue; i++) {
            pos = next[pos];
            if (pos == 21) break; // 도착하면 멈춤
        }

        // 겹침 체크
        if (pos != 21) { // 도착점이 아니면 겹침 체크
            for (int i = 0; i < 4; i++) {
                if (horse[i] == pos) {
                    return -1; // 다른 말과 겹침
                }
            }
        }

        return pos;
    }
}