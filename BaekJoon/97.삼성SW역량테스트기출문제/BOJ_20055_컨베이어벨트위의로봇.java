import java.io.*;
import java.util.*;

public class Main {

    static boolean[] robot;   // 로봇 존재 여부
    static int[] durability;  // 각 칸의 내구도
    static int n, k;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        durability = new int[n * 2];
        robot = new boolean[n * 2];
        for (int i = 0; i < n * 2; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int upIdx = 0;       // 올리는 위치 (고정)
        int downIdx = n - 1; // 내리는 위치 (고정)
        int step = 0;

        while (true) {
            step++;

            // 1. 벨트와 로봇이 함께 시계방향 회전
            rotate();
            robot[downIdx] = false; // 회전으로 내리는 위치에 도달한 로봇 즉시 제거

            // 2. 로봇들을 앞으로 이동 (뒤에서부터 처리해야 겹치지 않음)
            for (int i = n - 2; i >= 0; i--) {
                if (robot[i] && !robot[i + 1] && durability[i + 1] >= 1) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    durability[i + 1]--; // 이동한 칸의 내구도 감소
                }
            }

            // 3. 올리는 위치에 새 로봇 추가
            if (!robot[upIdx] && durability[upIdx] >= 1) {
                durability[upIdx]--;
                robot[upIdx] = true;
            }

            // 4. 이동으로 내리는 위치에 도달한 로봇 제거
            robot[downIdx] = false;

            // 5. 종료 조건: 내구도 0인 칸이 k개 이상
            int zeroCnt = 0;
            for (int i : durability) {
                if (i == 0) zeroCnt++;
            }
            if (zeroCnt >= k) break;
        }
        System.out.println(step);
    }

    // 벨트 시계방향 회전: 모든 원소를 한 칸씩 뒤로 이동
    static void rotate() {
        int tmpDur = durability[durability.length - 1];
        boolean tmpRobot = robot[robot.length - 1];

        for (int i = durability.length - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
            robot[i] = robot[i - 1];
        }
        durability[0] = tmpDur;
        robot[0] = tmpRobot;
    }
}