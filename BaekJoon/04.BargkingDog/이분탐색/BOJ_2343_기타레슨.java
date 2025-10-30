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
        int[] lesson = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(lesson, n, m));
    }

    static int binarySearch(int[] lesson, int n, int m) {
        int maxLength = 0;
        int left = 0;  // 최소 블루레이 크기 (가장 긴 강의)
        for (int length : lesson) {
            left = Math.max(left, length);
            maxLength += length;
        }
        int right = maxLength;  // 최대 블루레이 크기 (모든 강의 합)

        while (left <= right) {
            int blueRaySize = (left + right) / 2;  // 현재 시도할 블루레이 크기

            // 현재 크기로 몇 개의 블루레이가 필요한지 계산
            int usedCount = 1;
            int currentBlueRay = 0;
            for (int length : lesson) {
                if (currentBlueRay + length <= blueRaySize) {
                    currentBlueRay += length;  // 현재 블루레이에 담기
                } else {
                    currentBlueRay = length;   // 새 블루레이 시작
                    usedCount++;
                }
            }

            // M개 초과 필요하면 블루레이 크기 증가
            if (usedCount > m) {
                left = blueRaySize + 1;
            }
            // M개 이하면 더 작은 크기 시도
            else {
                right = blueRaySize - 1;
            }
        }
        return right + 1;  // 조건을 만족하는 최소 블루레이 크기
    }
}