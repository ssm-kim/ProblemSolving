import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static int[][] wheels = new int[4][8];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                wheels[i][j] = s.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int curIdx = Integer.parseInt(st.nextToken()) - 1;  // 회전시킬 톱니바퀴의 번호
            int dir = Integer.parseInt(st.nextToken());         // 방향 ( 1: 시계 방향, -1: 반시계 방향 )

            // 각 톱니바퀴의 회전 방향을 저장 (0: 회전안함, 1: 시계방향, -1: 반시계방향)
            int[] checkRotate = new int[4];
            checkRotate[curIdx] = dir;  // 현재 바퀴는 무조건 회전

            // 현재 바퀴 기준, 왼쪽 연쇄 회전 체크
            int[] start = wheels[curIdx];
            for (int left = curIdx - 1; left >= 0 ; left--) {
                int[] target = wheels[left];

                // 시작 바퀴의 6번 인덱스와 target의 2번 인덱스 비교, 만약 같다면 왼쪽 바퀴 회전 불가
                if (start[6] == target[2]) {
                    break;
                }
                // 다르면 왼쪽 바퀴 회전 및 start 바퀴 갱신
                else {
                    checkRotate[left] = checkRotate[left + 1] * -1;
                    start = target.clone();  // 다음 비교를 위해 기준점 이동
                }
            }

            // 현재 바퀴 기준, 오른쪽 연쇄 회전 체크
            start = wheels[curIdx];
            for (int right = curIdx + 1; right < 4; right++) {
                int[] target = wheels[right];

                // 시작 바퀴의 2번 인덱스와 target의 6번 인덱스 비교, 만약 같다면 오른쪽 바퀴 회전 불가
                if (start[2] == target[6]) {
                    break;
                }
                // 다르면 오른쪽 바퀴 회전 및 start 바퀴 갱신
                else {
                    checkRotate[right] = checkRotate[right - 1] * -1;
                    start = target.clone();  // 다음 비교를 위해 기준점 이동
                }
            }

            // 모든 톱니바퀴를 동시에 회전
            rotate(checkRotate);
        }

        // 최종 점수 합산 로직
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (wheels[i][0] == 1) {
                score += (int) Math.pow(2, i);
            }
        }
        System.out.println(score);
    }

    static void rotate(int[] checkRotate) {
        for (int i = 0; i < 4; i++) {
            int dir = checkRotate[i];

            if (dir == 1) {  // 시계 방향
                rightRotate(i);
            }
            else if (dir == -1) {  // 반시계 방향
                leftRotate(i);
            }
        }
    }

    static void leftRotate(int idx) {
        int first = wheels[idx][0];
        for (int i = 1; i < 8; i++) {
            wheels[idx][i - 1] = wheels[idx][i];
        }
        wheels[idx][8 - 1] = first;
    }

    static void rightRotate(int idx) {
        int last = wheels[idx][8 - 1];
        for (int i = wheels[idx].length - 2; i >= 0; i--) {
            wheels[idx][i + 1] = wheels[idx][i];
        }
        wheels[idx][0] = last;
    }
}