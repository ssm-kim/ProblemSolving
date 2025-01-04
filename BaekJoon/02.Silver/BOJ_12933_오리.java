import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static char[] quack = {'q', 'u', 'a', 'c', 'k'};

    static int qIdx = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] ch = br.readLine().toCharArray();
        boolean[] visited = new boolean[ch.length];

        if (ch[0] != 'q' || ch.length % 5 != 0) {
            System.out.println(-1);
            return;
        }  // 첫 번째 값이 'q'로 시작하지 않거나, 배열 길이가 5의 배수가 아닌 경우 -1

        for (int i = 0; i < ch.length; i++) {
            ArrayList<Character> list = new ArrayList<>();   // 방문 안 한 울음소리 배열

            for (int j = i; j < ch.length; j++) {
                if (!visited[j] && ch[j] == quack[qIdx]) {
                    qIdx++;
                    visited[j] = true;
                    list.add(ch[j]);
                }  // 미방문한 입력값과 quack 문자열 하나하나 비교

                if (qIdx == 5) {
                    qIdx = 0;
                }
            }

            if (!list.isEmpty()) {
                if (list.get(list.size() - 1) != 'k') {
                    System.out.println(-1);
                    return;
                }
                answer++;
            }
        }
        System.out.println(answer);
    }
}