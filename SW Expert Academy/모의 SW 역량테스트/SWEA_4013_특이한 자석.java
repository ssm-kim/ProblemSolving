import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            int k = Integer.parseInt(br.readLine());

            // 자석 4개를 회전 가능한 덱으로 관리
            LinkedList<Integer>[] magnet = new LinkedList[4];

            for (int i = 0; i < 4; i++) {
                magnet[i] = new LinkedList<>();
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i].offer(Integer.parseInt(st.nextToken()));
                }
            }

            // K번 회전 수행
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken()) - 1;  // 0-based 인덱스
                int direct = Integer.parseInt(st.nextToken());       // 1: 시계, -1: 반시계

                // 연쇄 회전할 자석들 찾기
                HashSet<int[]> target = simulate(number, direct, magnet);

                // 실제 회전 수행
                for (int[] info : target) {
                    int num = info[0];
                    int dir = info[1];

                    if (dir == 1) {  // 시계 방향
                        magnet[num].offerFirst(magnet[num].pollLast());
                    } else {  // 반시계 방향
                        magnet[num].offer(magnet[num].poll());
                    }
                }
            }

            // 점수 계산
            int score = 0;
            for (int i = 0; i < 4; i++) {
                if (magnet[i].get(0) == 1) {
                    switch (i) {
                        case 0: score += 1; break;
                        case 1: score += 2; break;
                        case 2: score += 4; break;
                        case 3: score += 8; break;
                    }
                }
            }
            System.out.println(String.format("#%d %d", (t + 1), score));
        }
    }

    static HashSet<int[]> simulate(int targetIdx, int direct, LinkedList<Integer>[] magnet) {
        HashSet<int[]> set = new HashSet<>();
        set.add(new int[] {targetIdx, direct});  // 본인은 무조건 회전

        // 0번 자석: 오른쪽으로만 전파
        if (targetIdx == 0) {
            if (magnet[0].get(2) != magnet[1].get(6)) {  // 0번 오른쪽 vs 1번 왼쪽
                set.add(new int[] {1, direct * -1});

                if (magnet[1].get(2) != magnet[2].get(6)) {
                    set.add(new int[] {2, direct});

                    if (magnet[2].get(2) != magnet[3].get(6)) {
                        set.add(new int[] {3, direct * -1});
                    }
                }
            }
        }
        // 1번 자석: 양방향 전파
        else if (targetIdx == 1) {
            // 왼쪽 체크
            if (magnet[1].get(6) != magnet[0].get(2)) {
                set.add(new int[] {0, direct * -1});
            }
            // 오른쪽 체크
            if (magnet[1].get(2) != magnet[2].get(6)) {
                set.add(new int[] {2, direct * -1});

                if (magnet[2].get(2) != magnet[3].get(6)) {
                    set.add(new int[] {3, direct});
                }
            }
        }
        // 2번 자석: 양방향 전파
        else if (targetIdx == 2) {
            // 오른쪽 체크
            if (magnet[2].get(2) != magnet[3].get(6)) {
                set.add(new int[] {3, direct * -1});
            }
            // 왼쪽 체크
            if (magnet[2].get(6) != magnet[1].get(2)) {
                set.add(new int[] {1, direct * -1});

                if (magnet[1].get(6) != magnet[0].get(2)) {
                    set.add(new int[] {0, direct});
                }
            }
        }
        // 3번 자석: 왼쪽으로만 전파
        else {
            if (magnet[3].get(6) != magnet[2].get(2)) {
                set.add(new int[] {2, direct * -1});

                if (magnet[2].get(6) != magnet[1].get(2)) {
                    set.add(new int[] {1, direct});

                    if (magnet[1].get(6) != magnet[0].get(2)) {
                        set.add(new int[] {0, direct * -1});
                    }
                }
            }
        }

        return set;
    }
}