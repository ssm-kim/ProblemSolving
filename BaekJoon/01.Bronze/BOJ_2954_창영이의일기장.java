import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static char[] check = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] words = br.readLine().toCharArray();

        for (int i = 0; i < words.length; i++) {
            char word = words[i];
            boolean isJump = false;

            for (char c : check) {
                if (word == c) {
                    isJump = true;
                    break;
                }
            }

            if (isJump) {
                sb.append(word);
                i += 2;
                continue;
            }  // 모음이 같다면 2칸 점프

            sb.append(word);
        }
        System.out.println(sb.toString());
    }
}