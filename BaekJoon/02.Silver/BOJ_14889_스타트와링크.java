import java.io.*;
import java.util.*;

public class Main {

    static int n, answer;
    static int[][] map;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        visit = new boolean[n];
        combinations(0, 0); // n개 중에 n/2개를 뽑는 조합
        System.out.println(answer);
    }

    static void combinations(int depth, int start) {
        if (depth == n / 2) {
            int startScore = 0;
            int linkScore = 0;

            // 스타트팀 과 링크팀을 분리해서 능력치 합을 각각 구한다,
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visit[i] && visit[j]) {
                        startScore += (map[i][j] + map[j][i]);
                    } else if (!visit[i] && !visit[j]) {
                        linkScore += (map[i][j] + map[j][i]);
                    }
                }
            }
            // 두 팀간의 차이에 대한 최솟값 갱신
            int diff = Math.abs(startScore - linkScore);
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = start; i < n; i++) {
            visit[i] = true;
            combinations(depth + 1, i + 1);
            visit[i] = false;
        }
    }
}