import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = s.length();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                set.add(s.substring(i, j));
            }
        }
        System.out.println(set.size());
   }
}