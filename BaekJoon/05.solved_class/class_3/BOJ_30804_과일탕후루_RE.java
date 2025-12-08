import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] fruits = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int lt = 0;
        int rt = 0;
        int answer = 0;

        while (rt < n) {
            // 과일 종류가 2개 이하면 오른쪽 확장
            if (map.size() <= 2) {
                map.put(fruits[rt], map.getOrDefault(fruits[rt], 0) + 1);
                rt++;
            }
            // 3개 이상이면 왼쪽 축소
            else {
                while (map.size() >= 3) {
                    map.put(fruits[lt], map.get(fruits[lt]) - 1);
                    if (map.get(fruits[lt]) == 0) {
                        map.remove(fruits[lt]);
                    }
                    lt++;
                }
            }

            // 2개 이하일 때만 최댓값 갱신
            if (map.size() <= 2) {
                answer = Math.max(answer, rt - lt);
            }
        }
        System.out.println(answer);
    }
}