import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] hackingCnt;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b); // a가 b를 해킹할 수 있다.
        }

        // 0번 인덱스 사용 X, 단방향 그래프
//        for (ArrayList<Integer> ints : arr) {
//            System.out.println(ints);
//        }

        hackingCnt = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];  // dfs 시작전 방문 배열 초기화
            dfs(i);
        }
        // System.out.println(Arrays.toString(hackingCnt));
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) { // 최대 해킹 가능한 컴퓨터 수 찾기
            maxCnt = Math.max(maxCnt, hackingCnt[i]);
        }

        StringBuilder sb = new StringBuilder();  // 최댓값인 컴퓨터 출력
        for (int i = 1; i <= n; i++) {
            if (hackingCnt[i] == maxCnt) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    static void dfs(int cur_v) {
        visited[cur_v] = true; // 현재 노드 방문

        for (int next_v : arr[cur_v]) {
            if (!visited[next_v]) {  // 다음 노드에 방문하지 않았다면
                hackingCnt[next_v]++;
                dfs(next_v);  // 해킹 가능 수를 누적
            }
        }
    }
}