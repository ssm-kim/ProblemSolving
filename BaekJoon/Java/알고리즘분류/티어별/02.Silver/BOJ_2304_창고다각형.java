import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<int[]> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int mIdx = 0, mHeight = 0;
        for (int i = 0; i < N; i++) {
            if (map.get(i)[1] > mHeight) {
                mIdx = i;
                mHeight = map.get(i)[1];
            }
        }

        // System.out.println("최대 높이 인덱스 : " + mIdx + "\n최대 높이 값 : " + mHeight + "\n");

        int answer = 0;
        int sx = map.get(0)[0];
        int sy = map.get(0)[1];
        for (int i = 1; i <= mIdx; i++) {
            int nx = map.get(i)[0];
            int ny = map.get(i)[1];

            if (ny > sy) {
                answer += (nx - sx) * sy;
                sx = nx;
                sy = ny;
            }  // 다음 번째 높이가 현재 높이보다 크다면
        }  // 왼쪽부터 최대 높이 전까지 면적 계산

        int ex = map.get(N - 1)[0];
        int ey = map.get(N - 1)[1];
        for (int i = N - 2; i >= mIdx; i--) {
            int nx = map.get(i)[0];
            int ny = map.get(i)[1];
            if (ny >= ey) {
                answer += (ex - nx) * ey;
                ex = nx;
                ey = ny;
            }  // 다음 번째 높이가 현재 높이보다 크다면
        }  // 오른쪽부터 최대 높이 전까지 면적 계산

        answer += mHeight;  // 최대 높이 계산

        System.out.println(answer);

    }
}