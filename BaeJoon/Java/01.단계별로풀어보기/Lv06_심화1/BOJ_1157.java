package SBSSol.Lv06_심화1;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BOJ_1157 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toUpperCase(); // 대문자 변경
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(str.split("")));
        HashMap<String, Integer> words = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            if (!words.containsKey(arr.get(i))) {  // 단어가 없다면 1로 초기화
                words.put(arr.get(i), 1);
            } else {
                words.put(arr.get(i), words.get(arr.get(i)) + 1);  // 단어가 있다면 +1
            }
        }

        int cntMax = Collections.max(words.values());  // 가장 많이 사용된 알파벳 갯수
        int check = 0;
        String ans = "";
        for (Entry <String, Integer> entrySet : words.entrySet()) {
            if (cntMax == entrySet.getValue()) {
                ans = entrySet.getKey();
                check += 1;
            }
        }

        if (check == 1) {
            System.out.println(ans);
        } else {
            System.out.println("?");
        }


    }
}
