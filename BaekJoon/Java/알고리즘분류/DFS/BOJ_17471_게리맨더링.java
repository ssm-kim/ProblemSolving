import java.io.*;
import java.util.*;

public class Main {

    static int n, m, answer;
    static int[] population;
    static boolean[] selected;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        population = new int[n + 1];   // 구역별 인구수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList[n + 1];  // 리스트 초기화
        for (int i = 1; i <= n; i++) map[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());  // 각 구역과 인접한 구역의 수
            for (int j = 0; j < m; j++) {
                int adjustInfo = Integer.parseInt(st.nextToken());  // 인접한 구역 정보
                map[i].add(adjustInfo);
            }
        } // 입력

        /**
         * 1. 구역을 2개의 선거구로 나눠야 한다. (각 구역은 두 선거구 중 하나에 포함)
         * 2. 선거구는 구역을 적어도 하나 포함.
         * 3. 한 선거구에 포함 되는 구역은 모두 연결
         */
        // n개 중에 1 ~ n-1개를 뽑는 조합
        selected = new boolean[n + 1];  // 방문 여부
        answer = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            m = i;  // 뽑는 갯수
            combination(0, 1);
        } // 문제 해결

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);  // 출력
    }

    static void combination(int depth, int start) {
        if (depth == m) {
            if (adjust()) {  // 각 선거구끼리 연결 되어 있는지?
                int aSum = 0, bSum = 0;
                for (int i = 1; i <= n; i++) {
                    if (selected[i]) {
                        aSum += population[i];
                    } else {
                        bSum += population[i];
                    }
                }
                int diff = Math.abs(aSum - bSum);
                answer = Math.min(answer, diff);
            }
            return;
        }

        for (int i = start; i <= n; i++) {  // 조합
            selected[i] = true;
            combination(depth + 1, i + 1);
            selected[i] = false;
        }
    }

    static boolean adjust() {
        // 한 번에 검사 (탐색 후 하나라도 false가 있다면 return)
        boolean[] visited = new boolean[n + 1];

        // 각 그룹의 시작점 추출
        int startA = -1, startB = -1;
        for (int i = 1; i <= n; i++) {
            if (startA != -1 && startB != -1) break;
            if (selected[i]) startA = i;
            if (!selected[i]) startB = i;
        }

        // 한 그룹이라도 시작점이 없다면 연결 X
        if (startA == -1 || startB == -1) return false;

//        boolean[] visitA = new boolean[n + 1];
//        boolean[] visitB = new boolean[n + 1];

        // 그룹 A는 true, 그룹 B는 false
        dfs(startA, visited, true);
        dfs(startB, visited, false);

        // 하나라도 false가 있다면 연결 안된 것
        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }

    // 인접 리스트 연결 여부 검사
    static void dfs(int cur_v, boolean[] visit, boolean isGroup) {
        visit[cur_v] = true;
        for (int next_v : map[cur_v]) {
            if (selected[next_v] == isGroup && !visit[next_v]) {
                dfs(next_v, visit, isGroup);
            }
        }
    }

    static void bfs(int start, boolean[] visit, boolean isGroup) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int cur_v = queue.poll();
            for (int next_v : map[cur_v]) {
                if (!visit[next_v] && selected[next_v] == isGroup) {
                    visit[next_v] = true;
                    queue.offer(next_v);
                }
            }
        }
    }
}