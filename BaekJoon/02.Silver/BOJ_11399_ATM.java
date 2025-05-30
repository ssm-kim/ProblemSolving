import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            // 키 : 순서, 값: 걸리는 시간
            map.put(i + 1, Integer.parseInt(st.nextToken()));
        }

        // 값 기준으로 오른차순 정렬
        List<Map.Entry<Integer, Integer>> entrySet = new ArrayList<>(map.entrySet());
        Collections.sort(entrySet, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> t1, Map.Entry<Integer, Integer> t2) {
                return Integer.compare(t1.getValue(), t2.getValue());
            }
        });

        int answer = 0;
        int time = 0;
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            time += entry.getValue();
            answer += time;
        }
        System.out.println(answer);
    }
}