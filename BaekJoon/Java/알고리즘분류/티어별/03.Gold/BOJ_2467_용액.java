import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] = sc.nextInt();
        }

        Arrays.sort(map);

        int minSum = Integer.MAX_VALUE;
        int res1 = 0, res2 = 0;

        for (int i = 0; i < n - 1; i++) {
            int lt = i + 1;  // 현재 용액 다음부터 탐색
            int rt = n - 1;
            while (lt <= rt) {
                int mid = (lt + rt) / 2;
                int sum = map[i] + map[mid];  // 두 용액의 합

                // 절댓값 합이 더 작은 합을 찾으면 결과 갱신
                if (Math.abs(sum) < Math.abs(minSum)) {
                    minSum = sum;
                    res1 = map[i];
                    res2 = map[mid];
                }

                if (sum < 0) {  // 합이 음수면 왼쪽 포인터를 오른쪽으로
                    lt = mid + 1;
                } else {
                    rt = mid - 1;
                }
            }
            System.out.println(res1 + " " + res2);
        }
    }
}