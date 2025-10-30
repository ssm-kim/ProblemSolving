import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);  // 이분탐색을 위한 정렬

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            // target의 개수 = 끝 위치 - 시작 위치
            int count = upperBound(cards, target) - lowerBound(cards, target);
            sb.append(count).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int lowerBound(int[] cards, int target) {
        // target이 시작되는 위치 찾기
        int left = 0;
        int right = cards.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (cards[mid] >= target) {  // target 이상이면 더 왼쪽으로
                right = mid;
            } else {                     // target 미만이면 더 오른쪽으로
                left = mid + 1;
            }
        }
        return left;
    }

    static int upperBound(int[] cards, int target) {
        // target이 끝나는 위치 + 1 찾기
        int left = 0;
        int right = cards.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (cards[mid] > target) {   // target 초과면 더 왼쪽으로
                right = mid;
            } else {                     // target 이하면 더 오른쪽으로
                left = mid + 1;
            }
        }
        return left;
    }
}