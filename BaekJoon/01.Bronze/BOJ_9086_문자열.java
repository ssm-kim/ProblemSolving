import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String str;

        for (int i = 0; i < T; i++) {
            str = br.readLine();
            System.out.println(str.charAt(0) + "" + str.charAt(str.length() - 1));
        }
    }
}