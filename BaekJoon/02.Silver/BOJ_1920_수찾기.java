import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int N, M;
    static int[] originLst;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 정수 리스트
        N = Integer.parseInt(br.readLine());
        originLst = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            originLst[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위한 정렬
        Arrays.sort(originLst);

        // M개의 정수 리스트 체크
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            binarySearch(target);  // 각 타겟 숫자마다 이진 탐색 수행
        }
    }

    static void binarySearch(int target) {
        int lt = 0;
        int rt = N - 1;
        boolean exist = false;
        while(lt <= rt) {
            int mid = (lt + rt) / 2;

            if (originLst[mid] > target) {
                rt = mid - 1;
            }  // 중간값이 타겟보다 크면 왼쪽 반으로 탐색 범위 좁힘
            else if (originLst[mid] < target) {
                lt = mid + 1;
            }  // 중간값이 타겟보다 작으면 오른쪽 반으로 탐색 범위 좁힘
            else {
                exist = true;
                break;
            }  // 타겟 숫자를 찾음.
        }
        System.out.println(exist ? 1 : 0);
    }
}