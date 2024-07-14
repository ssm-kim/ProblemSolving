import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visit;
    static int vertex, line;
    static Map<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        vertex = Integer.parseInt(br.readLine());  // 컴퓨터(정점)의 수
        line = Integer.parseInt(br.readLine());    // 간선의 수

        // 모든 정점에 대해 빈 리스트 초기화
        for (int i = 1; i <= vertex; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < line; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.get(start).add(end);
            map.get(end).add(start);  // 양방향 그래프
        }

        visit = new boolean[vertex + 1];
        System.out.println(bfs(1) - 1);  // 1번 컴퓨터를 제외한 감염된 컴퓨터의 수 출력
    }

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visit[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int cur_v = queue.poll();
            count++;

            for (int next : map.get(cur_v)) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.offer(next);
                }
            }
        }

        return count;
    }
}