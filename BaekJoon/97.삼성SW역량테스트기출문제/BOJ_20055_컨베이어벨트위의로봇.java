import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, k, zeroCnt;  // 컨베이어 길이, 종료조건, 내구도0인 칸 개수
    static int[] a;            // 각 칸의 내구도
    static boolean[] pos;      // 각 칸의 로봇 존재 여부

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        pos = new boolean[n * 2];
        zeroCnt = 0;
        int step = 0;
        while (true) {
            if (k <= zeroCnt) break;  // 내구도 0인 칸의 개수가 k 이상이면 종료
            step++;

            // 1. 벨트+로봇 회전
            rotate();
            pos[n - 1] = false;  // 내릴 수 있다면 즉시 내림.

            // 2. 로봇 이동 (뒤에서부터 = 먼저 올라간 순서)
            move();
            pos[n - 1] = false;  // 내릴 수 있다면 즉시 내림.

            // 3. 새 로봇 올리기
            if(!pos[0] && a[0] >= 1) {
                pos[0] = true;
                a[0]--;

                if (a[0] == 0) zeroCnt++;
            }
        }
        System.out.println(step);
    }

    static void move() {
        // 뒤에서 부터 순회한다. (가장 먼저 벨트에 올라간 로봇부터 확인)
        for (int i = n * 2 - 1; i >= 0; i--) {
            if (pos[i]) {  // 현재 칸에 로봇이 있다면
                // 이동하려는 칸에 로봇 없고, 그 칸의 내구도 1이상 남아 있는지 체크
                if (!pos[(i + 1) % (n * 2)] && a[(i + 1) % (n * 2)] >= 1) {
                    // 이동하려는 칸에 로봇 추가 + 내구도 1 감소
                    pos[(i + 1) % (n * 2)] = true;
                    a[(i + 1) % (n * 2)]--;
                    if (a[(i + 1) % (n * 2)] == 0) zeroCnt++;

                    // 현재 칸에 로봇 여부 제거
                    pos[i] = false;
                }
            }
        }
    }

    static void rotate() {
        // 시계방향 회전: 마지막 원소를 맨 앞으로

        // 내구도 이동
        int tmp = a[n * 2 - 1];  // 마지막 내구도 저장
        for (int i = n * 2 - 2; i >= 0; i--) {
            a[i + 1] = a[i];
        }
        a[0] = tmp;

        // 로봇 이동
        boolean robot = pos[n * 2 - 1];  // 마지막 로봇 여부 저장
        for (int i = n * 2 - 2; i >= 0; i--) {
            pos[i + 1] = pos[i];
        }
        pos[0] = robot;
    }
}