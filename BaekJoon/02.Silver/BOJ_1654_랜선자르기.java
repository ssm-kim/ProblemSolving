import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int n = sc.nextInt();

        long answer = Integer.MIN_VALUE;
        long[] cable = new long[k];
        for (int i = 0; i < k; i++) {
            cable[i] = sc.nextLong();
        }

        Arrays.sort(cable);  // 정렬

        long lt = 1, rt = cable[k - 1];
        while (lt <= rt) {
            long mid = (lt + rt) / 2;

            long cutCnt = 0; // 현재 랜선 갯수
            for (long i : cable) {
                cutCnt += (i / mid);
            }

            if (cutCnt >= n) {  // n개 이상 + 길이가 가장 긴 랜선
                answer = mid;
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }
        System.out.println(answer);
    }
}