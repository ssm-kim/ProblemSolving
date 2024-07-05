package D2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// [S/W 문제 해결 기본] 1일차 - 최빈수 구하기 (HasHMap 사용)

public class SWEA_1204 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            br.readLine();  // TestCase 동일  ->  첫째줄 Pass

            // String으로 값을 받아와서 공백기준으로 나누고 배열로 변경 후 ArrayList 삽입
            ArrayList<String> tmp = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
            HashMap<Integer, Integer> scoreCnt = new HashMap<>();  // <Integer, Integer> HashMap 선언

            for (String s : tmp) {  // HashMap (key,value) 저장
                int num = Integer.parseInt(s);  // 문자열  ->  int 변환
                if (!scoreCnt.containsKey(num)) {  // 만약 HashMap Key 없을 시 Value 1 초기화
                    scoreCnt.put(num, 1);
                } else {  // Key 있을 시 Value +1
                    scoreCnt.put(num, scoreCnt.get(num) + 1);
                }
            }

            int maxFrequency = Collections.max(scoreCnt.values());  // 최대 빈도 HashMap value 값

            int ans = 0;
            for (HashMap.Entry<Integer, Integer> kv : scoreCnt.entrySet()) {
                if (maxFrequency == kv.getValue()) {  // 최대 빈도 수 같다면
                    if (kv.getKey() > ans) {          // 더 큰 Key 값으로 갱신
                        ans = kv.getKey();
                    }
                }
            }
            System.out.printf("#%d %d\n", tc+1, ans);
        }
    }
}