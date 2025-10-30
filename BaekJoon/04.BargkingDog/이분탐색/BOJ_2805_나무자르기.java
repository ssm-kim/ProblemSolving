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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] treeHeight = new int[n];
        for (int i = 0; i < n; i++) {
            treeHeight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(treeHeight);
        long left = 0;                   // 절단기 최소 높이
        long right = treeHeight[n - 1];  // 절단기 최대 높이 (가장 높은 나무)
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;  // 현재 시도할 절단기 높이

            // mid 높이로 잘랐을 때 얻는 나무 총 길이
            long getTreeLength = 0;
            for (int height : treeHeight) {
                if (height > mid) {
                    getTreeLength += height - mid;
                }
            }

            // m 이상 얻을 수 있으면 절단기를 더 높게 설정 가능
            if (getTreeLength >= m) {
                left = mid + 1;
                answer = Math.max(answer, mid);  // 가능한 최대 높이 갱신
            }
            // m 미만이면 절단기를 더 낮게 설정해야 함
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}