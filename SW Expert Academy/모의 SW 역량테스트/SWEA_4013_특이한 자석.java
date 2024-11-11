import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int K;
    static LinkedList<Integer>[] magnetInfo = new LinkedList[6];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            K = Integer.parseInt(br.readLine());  // 자석을 회전시키는 횟수
            for (int i = 0; i <= 5; i++) magnetInfo[i] = new LinkedList<>();  // 덱 생성 (0번 인덱스 사용 안함)
            for (int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnetInfo[i].offer(Integer.parseInt(st.nextToken()));
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());   // 자석 번호
                int direct = Integer.parseInt(st.nextToken());  // 회전 방향 1: 시계 방향, -1: 반시계 방향
                rotateLogics(start, direct);
            }

            int answer = 0, score = 1;
            for (int i = 1; i < magnetInfo.length - 1; i++) {
                if (magnetInfo[i].get(0) == 1) {
                    answer += score;
                }
                score *= 2;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void rotateLogics(int start, int direct) {
        HashSet<Integer> process = new LinkedHashSet<>();
        process.add(start);  // 현재 번호

        // 서로 맞닿아 있는 인덱스 2번, 6번 로직 처리
        if (start == 1) {
            for (int i = 1; i < 4; i++) {
                if (magnetInfo[i].get(2) == magnetInfo[i + 1].get(6)) break;  // 서로 같으면 정지
                process.add(i);
                process.add(i + 1);
            }
        }  // 왼쪽 끝일 때
        else if (start == 4) {
            for (int i = 4; i > 1; i--) {
                if (magnetInfo[i].get(6) == magnetInfo[i - 1].get(2)) break;
                process.add(i);
                process.add(i - 1);
            }
        }  // 오른쪽 끝일 때
        else {
            for (int i = start; i < 4; i++) {
                if (magnetInfo[i].get(2) == magnetInfo[i + 1].get(6)) break;
                process.add(i);
                process.add(i + 1);
            }  // start 에서 오른쪽 탐색
            for (int i = start; i > 1; i--) {
                if (magnetInfo[i].get(6) == magnetInfo[i - 1].get(2)) break;
                process.add(i);
                process.add(i - 1);
            }  // start 에서 왼쪽 탐색
        }  // 중간 값 2 또는 3일 때

        int[] dirs = new int[4];
        dirs[start - 1] = direct;
        int lt = start - 2, rt = start, dirCopy = direct;
        while (lt >= 0 || rt <= 4) {
            dirCopy *= -1;
            if (0 <= lt && lt < 4) dirs[lt] = dirCopy;
            if (0 <= rt && rt < 4) dirs[rt] = dirCopy;
            lt--;
            rt++;
        }  // 방향 값 추출

        Integer[] processArr = process.toArray(new Integer[0]);
        for (int idx : processArr) {
            int dir = dirs[idx - 1]; // 어느 방향으로 돌릴지 선택

            if (dir == 1) {
                magnetInfo[idx].addFirst(magnetInfo[idx].pollLast());
            }  // 시계 방향
            else {
                magnetInfo[idx].addLast(magnetInfo[idx].pollFirst());
            }  // 반시계 방향
        }
    }
}