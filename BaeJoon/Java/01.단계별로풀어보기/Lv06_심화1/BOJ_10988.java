package SBSSol.Lv06_심화1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class BOJ_10988 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> word = Arrays.asList(br.readLine().split(""));
        
        int answer = 1;
        for (int i = 0; i < word.size() / 2; i++) {
            if (!word.get(i).equals(word.get(word.size() - i - 1))) {  // 팰린드롬이 아니면 중지
                answer = 0;
                break;
            }
        }
        System.out.println(answer);
    }
}
