import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Map<Integer, Integer> cards = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            cards.put(num, cards.getOrDefault(num, 0) + 1);
        }

        int M = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int search = sc.nextInt();
            sb.append(cards.get(search) == null ? 0 : cards.get(search)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}