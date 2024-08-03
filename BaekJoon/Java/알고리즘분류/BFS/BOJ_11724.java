import java.io.*;
import java.util.*;

public class Main {

    static int n, m, ans;
    static List<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new ArrayList[n + 1];    // 인덱스 0번 사용 X

        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();  // arr 배열 초기화
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            arr[u].add(v);  // 무방향 그래프
            arr[v].add(u);
        }

        for (int i = 0; i < n + 1; i++) {
            System.out.println(arr[i]);
        }

        // bfs 풀이
        ans = 0;
        visited = new boolean[n + 1];  // 방문 배열 초기화
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                ans++;
                bfs(i);
            }
        }

        // dfs (재귀) 풀이
        ans = 0;
        visited = new boolean[n + 1];  // 방문 배열 초기화
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                dfs(i);
                ans++;
            }
        }
        // dfs (스택) 풀이
        ans = 0;
        visited = new boolean[n + 1];  // 방문 배열 초기화
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                dfsStack(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

    // dfs (스택)
    static void dfsStack(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int cur_v = stack.peek();
            boolean check = false;    // 다음 정점 발견 여부

            for (int next_v : arr[cur_v]) {
                if (!visited[next_v]) {
                    visited[next_v] = true;
                    check = true;
                    stack.push(next_v);
                    break;
                }
            }
            if (!check) stack.pop();  // 현재 정점에서 연결된 정점을 찾지 못했다면 최상위 정점 pop
        } // end while
    }

    // dfs (재귀)
    static void dfs(int cur_v) {
        visited[cur_v] = true;

        for (int next_v : arr[cur_v]) {
            if (!visited[next_v]) {
                dfs(next_v);
            }
        }
    }

    // bfs (큐)
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;  // 현재 정점 확인
        while (!queue.isEmpty()) {
            int cur_v = queue.poll();

            for (int next_v : arr[cur_v]) {
                if (!visited[next_v]) {
                    queue.offer(next_v);     // 큐에 방문할 정점 삽입
                    visited[next_v] = true;  // 다음 정점 확인
                }
            }
        }
    }
}