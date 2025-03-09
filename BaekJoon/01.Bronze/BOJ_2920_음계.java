import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int[] scale = new int[8];
    static int[] valid = new int[8];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 8; i++) {
            scale[i] = Integer.parseInt(st.nextToken());
            valid[i] = i + 1;
        }

        // 오름차순 검증
        boolean isAscending = true;
        for (int i = 0; i < 8; i++) {
            if (scale[i] != valid[i]) {
                isAscending = false;
                break;
            }
        }

        // 내림차순 검증
        boolean isDescending = true;
        for (int i = 0; i < 8; i++) {
            if (scale[i] != valid[7 - i]) {
                isDescending = false;
                break;
            }
        }
        
        // 결과 출력
        if (isAscending) {
            System.out.println("ascending");
        } else if (isDescending) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}

//public class Main {
//
//    static int[] scale = new int[8];
//    static int[] ascending = {1, 2, 3, 4, 5, 6, 7, 8};
//    static int[] descending = {8, 7, 6, 5, 4, 3, 2, 1};
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        for (int i = 0; i < 8; i++) {
//            scale[i] = Integer.parseInt(st.nextToken());
//        }
//
//        if (Arrays.equals(scale, ascending)) {
//            System.out.println("ascending");
//        } else if (Arrays.equals(scale, descending)) {
//            System.out.println("descending");
//        } else {
//            System.out.println("mixed");
//        }
//    }
//}