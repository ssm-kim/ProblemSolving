import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static List<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (tmp[j] == 'Y') {
                    edges[i].add(j + 1);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int cur = check(i);
            answer = Math.max(answer, cur);
        }
        System.out.println(answer);
    }

    static int check(int start) {
        Set<Integer> set = new HashSet<>();
        
        for (int next : edges[start]) {
            if (next != start) {
                set.add(next);
            }  // 자기 자신이 아니고 직접 친구 추가

            for (int nextOfNext : edges[next]) {
                if (nextOfNext != start) {
                    set.add(nextOfNext);
                }  // 자기 자신이 아니고 직접 친구의 친구 추가
            }
        }
        return set.size();
    }
}