import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] students;
    static int[] inDegree;  // 진입 차수
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 0번째 인덱스 사용 X
        inDegree = new int[N + 1];
        students = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) students[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            students[from].add(to);
            inDegree[to]++;  // 진입 차수 +1
        }

        // 위상 정렬
        boolean notCycle = topologicalSort();

        if (notCycle) {
            for (int i : answer) {
                System.out.print(i + " ");
            }
        }  // 싸이클이 없다면 출력
    }

    static boolean topologicalSort() {
        LinkedList<Integer> queue = new LinkedList<>();
        int cnt = 0;  // 방문한 정점의 개수 (싸이클 여부 확인)

        // 1. 진입 차수가 0인 정점을 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            // 2. 큐에서 진입 차수 0 인 정점을 꺼낸다.
            int cur_v = queue.poll();
            answer.add(cur_v);
            cnt++;

            // 3. 현재 정점의 진입 차수 -1 (현재 정점의 인접한 모든 간선 제거)
            for (int edge : students[cur_v]) {
                inDegree[edge]--;
                if (inDegree[edge] == 0) {
                    queue.offer(edge);
                }  // 4. 간선 제거 후 진입 차수 0이면 큐에 넣는다.
            }
        }
        return N == cnt;  // 싸이클이 존재하면 서로 다름.
    }
}
