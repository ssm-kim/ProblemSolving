import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 막대기의 길이를 저장한 배열 (2^0 ~ 2^6)
    static int[] map = {1, 2, 4, 8, 16, 32, 64};
    static int X;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        subset(0, 0, 0);
        System.out.println(answer);
    }

    static void subset(int depth, int sum, int cnt) {
        if (sum == X) {
            answer = Math.min(answer, cnt);
            return;
        }  // 목표 길이를 만들었다면, 사용한 막대 개수의 최솟값 갱신

        if (depth == 7) {
            return;
        }  // 모든 막대를 고려했다면 종료

        // 현재 막대를 선택하지 않는 경우
        subset(depth + 1, sum, cnt);

        // 현재 막대를 선택한 경우
        subset(depth + 1, sum + map[depth], cnt + 1);
    }
}