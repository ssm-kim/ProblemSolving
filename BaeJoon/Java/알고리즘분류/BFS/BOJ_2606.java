package algoClassification.BFS;

import java.io.*;
import java.util.*;

public class BOJ_2606 {

    static boolean [] visit;
    static int vertex, line, cnt;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        vertex = Integer.parseInt(br.readLine());  // 컴퓨터(정점)의 수
        line = Integer.parseInt(br.readLine());    // 간선의 수

        // 모든 정점에 대한 빈 리스트 초기화
        for (int i = 1; i <= vertex; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < line; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.get(start).add(end); // 양방향 그래프
            map.get(end).add(start);
        }

        visit = new boolean[vertex + 1];
        bfs(1);  // 1번 바이러스 개수를 구하므로 출발지 1로 고정
        System.out.println(cnt);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visit[start] = true;  // 출발지 방문 체크

        while (!queue.isEmpty()) {
            int cur_v = queue.poll();  // 최상위 노드 pop

            for (int next : map.get(cur_v)) {
                if (!visit[next]) {  // 다음 노드를 방문하지 않았다면 큐에 추가
                    visit[next] = true;
                    queue.offer(next);
                    cnt += 1;
                }
            }
        }
    }
}