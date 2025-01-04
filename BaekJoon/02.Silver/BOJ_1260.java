import java.io.*;
import java.util.*;

public class Main {

    // 인접 행렬 - dfs, bfs
    // 인접 리스트 - dfs(재귀, 스택), bfs(큐)

    static int n, m, v;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;         // 방문 확인
    static StringBuilder st;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();
        arr = new ArrayList[n + 1];  // 0번 인덱스 사용 X

        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            addEdge(v1, v2);  // 양방향 간선 추가 메서드
        }

        for (int i = 0; i < n + 1; i++) {  // 각 정점별로 정렬
            Collections.sort(arr[i]);
        }

        for (ArrayList arrayList : arr) {
            System.out.println(arrayList);
        }

        // 인접 리스트(재귀) - dfs
        visited = new boolean[n + 1];
        st = new StringBuilder();
        dfs(v, st.append(v));  // (출발 정점, 현재 정점 저장) 초기 값은 (v, -1)
        System.out.println(st);

        // 인접 리스트(스택) - dfs
        visited = new boolean[n + 1];
        st = new StringBuilder();
        dfsStack(v, st.append(v));
        System.out.println(st);

        // 인접 리스트(큐) - bfs
        visited = new boolean[n + 1];
        st = new StringBuilder();
        bfs(v, st.append(v));  // (출발 정점, 현재 정점 저장)
        System.out.println(st);

        // 인접 행렬 - bfs
        // 인접 행렬 - dfs

    }

    static void addEdge(int v1, int v2) {
        arr[v1].add(v2);
        arr[v2].add(v1);
    }

    static StringBuilder dfs(int cur_v, StringBuilder res) {
        visited[cur_v] = true;  // 방문 확인
        for (int next_v : arr[cur_v]) {
            if (!visited[next_v]) {
                dfs(next_v, res.append(" ").append(next_v));
            }
        }
        return res;
    }

    static StringBuilder bfs(int start, StringBuilder res) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);     // 방문할 정점 삽입
        visited[start] = true;  // 방문 확인

        while (!queue.isEmpty()) {       // FIFO
            int cur_v = queue.poll();    // 현재 정점 pop
            for (int next_v : arr[cur_v]) {
                if (!visited[next_v]) {  // 다음 정점 방문 안 했을 시 push
                    queue.offer(next_v);
                    visited[next_v] = true;
                    res.append(" ").append(next_v);   // 방문 체크 했으면 값 삽입
                }
            } // end for
        } // end while
        return res;
    }

    static StringBuilder dfsStack(int start, StringBuilder res) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);       // 방문할 정점 삽입
        visited[start] = true;   // 방문 확인

        while (!stack.isEmpty()) {      // LIFO
            int cur_v = stack.peek();   // 현재 정점을 확인
            boolean found = false;      // 인접 정점 발견 여부

            for (int next_v : arr[cur_v]) {
                if (!visited[next_v]) {  // 다음 정점 방문 안 했을 시 push
                    stack.push(next_v);
                    visited[next_v] = true;
                    res.append(" ").append(next_v);  // 방문 체크 했으면 값 삽입
                    found = true;

                    break;   // 현재 정점에서 더 이상 방문할 정점이 없으므로 break;
                }
            } // end for
            if (!found) {
                stack.pop(); // 더 이상 방문할 정점이 없으므로 현재 정점을 pop
            }
        }
        return res;
    }
}