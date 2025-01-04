import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int n = sc.nextInt();
        int[] map = new int[l + 1]; // 케이크 배열 (0은 아직 안 잘린 부분)

        int maxPieces = 0;
        int maxGuest = 0;

        // 방청객이 예상하는 가장 많은 조각을 받을 것으로 기대한 방청객 찾기
        for (int guest = 1; guest <= n; guest++) {
            int p = sc.nextInt();
            int k = sc.nextInt();

            int diff = k - p;
            if (diff > maxPieces) {
                maxPieces = diff;
                maxGuest = guest;
            }

            // 케이크 배열에 조각 표시
            for (int i = p; i <= k; i++) {
                if (map[i] == 0) { // 케이크의 조각이 아직 누구에게도 할당되지 않은 경우
                    map[i] = guest;
                }
            }
        }
        // System.out.println(Arrays.toString(map));


        // 실제로 가장 많은 조각을 받은 방청객 찾기
        int[] getPieces = new int[n + 1]; // 각 방청객이 받은 실제 조각 수
        for (int i = 1; i <= l; i++) {
            if (map[i] != 0) {
                getPieces[map[i]]++;
            }
        }

        int realMaxPieces = 0;
        int realMaxGuest = 0;

        for (int guest = 1; guest <= n; guest++) {
            if (getPieces[guest] > realMaxPieces) {
                realMaxPieces = getPieces[guest];
                realMaxGuest = guest;
            }
        }

        // 출력
        System.out.println(maxGuest);
        System.out.println(realMaxGuest);
    }
}
