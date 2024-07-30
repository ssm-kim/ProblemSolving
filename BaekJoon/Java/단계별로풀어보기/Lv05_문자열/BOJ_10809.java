package SBSSol.Lv05_문자열;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ_10809 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> alpha = new HashMap<>();
        for (int i = 97; i < 123; i++) {
            alpha.put((Character.toString((char) i)), -1);
        }

        ArrayList<String> word = new ArrayList<String>(Arrays.asList(br.readLine().split("")));
        for (int i = 0; i < word.size(); i++) {
            String target = word.get(i);
            if (alpha.get(target) == -1) {
                alpha.put(target, i);
            }
        }
        for (int i = 0; i < alpha.values().size(); i++) {
            System.out.print(alpha.values().toArray()[i] + " ");
        }
    }
}
