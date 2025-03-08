import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String checkWord;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            checkWord = br.readLine();
            if (checkWord.equals("0")) break;

            boolean palindrome = isPalindrome();
            System.out.println(palindrome ? "yes" : "no");
        }
    }

    static boolean isPalindrome() {
        for (int i = 0; i < checkWord.length() / 2; i++) {
            char start = checkWord.charAt(i);
            char end = checkWord.charAt(checkWord.length() - i - 1);
            if (start != end ) {
                return false;
            }
        }
        return true;
    }
}