import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] numbers, select;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 사전순 출력을 위해 입력 배열 정렬
        Arrays.sort(numbers);

        select = new int[m];
        combinations(0, 0);

        // LinkedHashSet → 배열 변환 (중복 제거 + 삽입 순서 보장)
        String[] setToString = set.toArray(new String[0]);

        sb.setLength(0);
        for (String s : setToString) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }

    static void combinations(int depth, int start) {
        // 종료 조건: m개 모두 선택했을 때
        if (depth == m) {
            for (int i : select) sb.append(i).append(" ");
            set.add(sb.toString()); // 중복 수열 자동 제거
            sb.setLength(0);
            return;
        }

        // 백트래킹: start부터 끝까지 탐색
        for (int i = start; i < n; i++) {
            select[depth] = numbers[i];
            combinations(depth + 1, i + 1); // 중복 없는 조합 (같은 원소 재선택 불가)
        }
    }
}