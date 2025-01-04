import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R, sequence;
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

        for (ArrayList<Integer> integers : map) {
            System.out.println(integers);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(map[i]);
        }  // 오름차순 정렬

        System.out.println();
        answer = new int[N + 1];
        dfs(R, 1);
        System.out.println();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }

    // 리턴 값 사용
    static int dfs(int cur, int seq) {
        answer[cur] = seq;

        for (int next : map[cur]) {
            if (answer[next] == 0) {
                seq = dfs(next, seq + 1);
            }
        }
        return seq;
    }

    // 전역 변수 사용
    static void dfs1(int cur) {
        answer[cur] = sequence;

        for (int next : map[cur]) {
            if (answer[next] == 0) {
                sequence++;
                dfs1(next);
            }
        }
    }
}
