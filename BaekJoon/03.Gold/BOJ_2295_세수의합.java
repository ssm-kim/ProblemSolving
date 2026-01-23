import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int n;
    static int[] u;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        u = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = Integer.parseInt(br.readLine());
        }

        // 핵심 아이디어: x + y + z = k를 변형하면 x + y = k - z
        // 두 수의 합(x+y)을 미리 저장해두고, k-z 값이 존재하는지 이진탐색으로 확인

        // 가능한 모든 두 수의 합(x+y)을 리스트에 저장 - O(n²)
        ArrayList<Integer> frontSumLst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                frontSumLst.add(u[i] + u[j]);
            }
        }

        // 이진탐색을 위한 정렬 - O(n² log n²)
        Collections.sort(frontSumLst);
        Arrays.sort(u);

        // 가장 큰 k부터 확인 (k = x+y+z를 만족하는 가장 큰 값 찾기)
        // k-z 값이 frontSumLst에 있는지 이진탐색으로 확인 - O(n^2 log n^2)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int k = u[i];
                int z = u[j];
                int target = k - z;  // x + y를 의미

                if (binarySearch(target, frontSumLst)) {
                    System.out.println(k);
                    return;
                }
            }
        }
    }

    static boolean binarySearch(int target, ArrayList<Integer> frontSumLst) {
        int lt = 0;
        int rt = frontSumLst.size() - 1;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (target == frontSumLst.get(mid)) return true;
            if (target > frontSumLst.get(mid)) {
                lt = mid + 1;
            }
            else {
                rt = mid - 1;
            }
        }
        return false;
    }
}