import java.io.*;
import java.util.*;

public class Main {

    static int V, E;
    static int[] inDegree;
    static ArrayList<Integer> answer = new ArrayList<>();
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());  // 학생 수 (노드)
        E = Integer.parseInt(st.nextToken());  // 키를 비교한 회수 (간선)
        map = new ArrayList[V + 1];  // 1번 인덱스 사용 X
        inDegree = new int[V + 1];   // 모든 노드에 대한 진입 차수는 0으로 초기화

        for (int i = 1; i <= V; i++) {
            map[i] = new ArrayList<>();
        }  // 리스트 초기화 (0번 인덱스 사용 X)

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].add(b);  // 정점 A가 정점 B 앞에 있어야 된다.
            inDegree[b] += 1;  // 정점 B로 들어오는 간선 +1
        }

        topologyBFS();
        for (Integer i : answer) {
            System.out.print(i + " ");
        }
    }

    static void topologyBFS() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }  // 처음 시작 시 진입 차수가 0인 노드를 큐에 삽입

        while (!queue.isEmpty()) {
            int cur_v = queue.poll();
            answer.add(cur_v);

            for (int edge : map[cur_v]) {
                inDegree[edge] -= 1;
                if (inDegree[edge] == 0) {
                    queue.offer(edge);
                }  // 새롭게 진입 차수가 0이 되는 노드 큐에 삽입
            }  // 해당 원소와 연결된 노드들의 진입 차수에서 1 빼기
        }
    }
}