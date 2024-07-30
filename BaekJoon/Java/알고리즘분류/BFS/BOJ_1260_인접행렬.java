package algoClassification.BFS;

import java.io.*;
import java.util.*;

public class BOJ_1260_인접행렬 {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;
    static int[][] arr;
    static int n, m, start;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());  // 정점의 개수
        m = Integer.parseInt(st.nextToken());  // 간선의 개수
        start = Integer.parseInt(st.nextToken());  // 탐색 시작할 정점의 번호

        arr = new int[n + 1][n + 1];  // 2차원 배열 생성 (0번째 인덱스 row, col 사용 X)

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            arr[num1][num2] = arr[num2][num1] = 1;  // 인접 행렬 풀이
        }

        visit = new boolean[n+1];     // 방문 여부 확인 배열
        dfs(start);

        sb.append("\n");

        visit = new boolean[n+1];     // 방문 여부 확인 배열
        bfs(start);
        System.out.println(sb);
    }

    static void dfs(int start) {
        visit[start] = true;
        sb.append(start + " ");

        for (int i = 0; i <= n; i++) {
            if (arr[start][i] == 1 && !visit[i]) {  // 간선이 있고 방문하지 않은 노드일 때
                dfs(i);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);   // 시작 노드 방문
        visit[start] = true;  // 방문 체크

        while (!queue.isEmpty()) {  // 방문할 노드가 있다면 실행
            int curNode = queue.poll();  // 큐 최상위 노드 pop
            sb.append(curNode + " ");  // 문자열 추가

            for (int i = 1; i <= n; i++) {
                if (arr[curNode][i] == 1 && !visit[i]) { // 방문을 하지 않았다면
                    queue.offer(i);
                    visit[i] = true;
                }
            }
        }
    }
}