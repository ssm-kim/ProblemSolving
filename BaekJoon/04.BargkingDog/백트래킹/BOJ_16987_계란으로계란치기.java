import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, maxBroken;
    static int[] durability;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        durability = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        maxBroken = 0;
        backTrack(0, 0);
        System.out.println(maxBroken);
    }

    static void backTrack(int depth, int brokenCnt) {
        // 모든 계란 탐색 완료 시 최댓값 갱신
        if (depth == n) {
            maxBroken = Math.max(maxBroken, brokenCnt);
            return;
        }

        // 현재 계란이 이미 깨진 경우 다음으로 진행
        if (durability[depth] <= 0) {
            backTrack(depth + 1, brokenCnt);
            return;
        }

        boolean hasTarget = false;
        for (int target = 0; target < n; target++) {
            // 자기 자신이거나 이미 깨진 계란은 제외
            if (target == depth || durability[target] <= 0) continue;

            hasTarget = true;
            // 계란 충돌 처리 (내구도 감소)
            durability[target] -= weight[depth];
            durability[depth] -= weight[target];

            // 깨진 계란 개수 계산
            int broken = 0;
            if (durability[depth] <= 0) broken++;
            if (durability[target] <= 0) broken++;

            backTrack(depth + 1, brokenCnt + broken);

            // 백트래킹: 상태 복구
            durability[target] += weight[depth];
            durability[depth] += weight[target];
        }

        // 칠 수 있는 계란이 없는 경우 다음으로 진행
        if (!hasTarget) {
            backTrack(depth + 1, brokenCnt);
        }
    }
}