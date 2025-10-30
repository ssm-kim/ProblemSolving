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
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);  // 이분탐색 전제조건: 정렬된 배열

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            boolean isSearch = binarySearch(target, numbers, n);
            System.out.println(isSearch ? 1 : 0);
        }
    }

    static boolean binarySearch(int target, int[] numbers, int n) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (numbers[mid] == target) {  // 찾음!
                return true;
            }
            else if (numbers[mid] > target) {  // 중간값이 크면 → 왼쪽 구간 탐색
                right = mid - 1;
            }
            else {  // 중간값이 작으면 → 오른쪽 구간 탐색
                left = mid + 1;
            }
        }

        return false;  // 못 찾음
    }
}