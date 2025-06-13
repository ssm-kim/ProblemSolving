import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(br.readLine());

        Object[] arr = set.toArray();
        String[] words = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            words[i] = (String) arr[i];
        }

        // 길이가 짧은 것부터 같으면 사전 순으로 (중복 제거)
        Arrays.sort(words, new Comparator<String>() {
           @Override
           public int compare(String s1, String s2) {
               if (s1.length() != s2.length()) {
                   return Integer.compare(s1.length(), s2.length());
               } else {
                   return s1.compareTo(s2);
               }
           }
        });

        for (String word : words) {
            System.out.println(word);
        }

    }
}