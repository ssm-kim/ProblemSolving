import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int N, M;
    static int[] openCards, myCards, answer;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 상대방 카드 입력
        N = Integer.parseInt(br.readLine());
        openCards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            openCards[i] = Integer.parseInt(st.nextToken());
        }

        // 내 카드 입력
        M = Integer.parseInt(br.readLine());
        myCards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            myCards[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위해 상대방 카드 정렬
        Arrays.sort(openCards);
        answer = new int[M];

        // 각 카드 개수 계산
        for (int i = 0; i < M; i++) {
            int low = lowerBound(myCards[i], 0, openCards.length);
            int up = upperBound(myCards[i], 0, openCards.length);
            answer[i] = up - low;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    // target이 mid 위치의 값보다 크면 오른쪽 탐색
    static int lowerBound(int target, int lt, int rt) {
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (target <= openCards[mid]) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        return lt;
    }

    // target 값을 초과하는 값이 처음 나타나는 위치
    static int upperBound(int target, int lt, int rt) {
        while(lt < rt) {
            int mid = (lt + rt) / 2;
            if (target < openCards[mid]) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        return lt;
    }
}