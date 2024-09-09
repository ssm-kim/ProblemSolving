import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R, sequence = 1;
    static boolean[] visited;
    static int[] answer;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }  // 리스트 초기화

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s].add(e);
            map[e].add(s);
        }  // 양방향 그래프

        for (int i = 1; i <= N; i++) {
            Collections.sort(map[i]);
        }  // 오름차순 정렬

        visited = new boolean[N + 1];
        answer = new int[N + 1];
        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cur) {
        visited[cur] = true;
        answer[cur] = sequence;

        for (int next : map[cur]) {
            if (!visited[next]) {
                sequence++;
                dfs(next);
            }
        }
    }
}
