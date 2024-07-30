package SBSSol.Lv05_문자열;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ_5622 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> words = new ArrayList<>(Arrays.asList(br.readLine().split("")));
        HashMap<String, Integer> dial = new HashMap<>(){{
            put("ABC", 2); put("DEF", 3);  put("GHI", 4); put("JKL", 5);
            put("MNO", 6); put("PQRS", 7); put("TUV", 8); put("WXYZ", 9);
        }};

        int minTime = 2; // 숫자 1을 걸려면 총 2초가 필요  ->  기본값 2

        for (String target : words) {  // 찾는 알파벳 값
            for (String key : dial.keySet()) {
                if (key.contains(target)) {  // 알파벳 키값에 포함 된다면
                    minTime += (dial.get(key));
                    break;
                }
            }
        }
        System.out.println(minTime);
    }
}
