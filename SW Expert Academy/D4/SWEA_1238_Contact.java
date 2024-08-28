import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {

    static int n, depth;
    static ArrayList<Integer>[] map;
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            n = sc.nextInt();
            int v = sc.nextInt();

            map = new ArrayList[101];
            for (int i = 0; i <= 100; i++) {  // 리스트 초기화
                map[i] = new ArrayList<>();
            }

            depth = 0;
            distance = new int[101];
            visited = new boolean[101];
            for (int i = 0; i < n / 2; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map[from].add(to);
            }

            bfs(v);
            int answer = 0;
            for (int i = 0; i < 101; i++) {
                if (depth == distance[i]) {
                    answer = Math.max(i, answer);
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map[cur]) {  // 현재 정점에 연결된 정점들 방문
                if (!visited[next]) {
                    visited[next] = true;  // 다음 정점 방문 표시
                    distance[next] = distance[cur] + 1;       // 다음 정점에 현재 정점 +1 (누적)
                    depth = Math.max(depth, distance[next]);  // 최대 깊이 갱신
                    queue.offer(next);
                }
            }
        }
    }
}