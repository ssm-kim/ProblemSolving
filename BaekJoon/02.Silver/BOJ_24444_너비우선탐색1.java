import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static ArrayList<Integer>[] edges;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        // 리스트 초기화
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            edges[start].add(end);
            edges[end].add(start);
        }

        // 오릋차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(edges[i]);
        }

        // 방문 순서 배열 초기화
        seq = new int[N + 1];
        bfs(R, 1);

        for (int i = 1; i <= N; i++) {
            System.out.println(seq[i]);
        }
    }

    static void bfs(int start, int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        seq[start] = num;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : edges[cur]) {
                if (seq[next] == 0) {
                    seq[next] = ++num;
                    queue.offer(next);
                }  // 방문 안 헀으면
            }
        }
    }
}