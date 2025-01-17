import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int TotalPopulation;
    static int N;
    static int minDiff = Integer.MAX_VALUE;
    static int[] peoples;
    static boolean[] visited;
    static ArrayList<Integer>[] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        peoples = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
            TotalPopulation += peoples[i];  // 전체 인구 수
        }

        map = new ArrayList[N];
        for (int i = 0; i < N; i++) map[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());

            for (int j = 0; j < vertex; j++) {
                int edge = Integer.parseInt(st.nextToken()) - 1;
                map[i].add(edge);
            }
        }

        visited = new boolean[N];
        for (int i = 1; i < N; i++) {
            combinations(0, 0, i, 0);
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static void combinations(int depth, int start, int r, int curPopulation) {
        if (depth == r) {

            // 첫 번째 선거구 연결성 체크
            if (!isConnected(true)) return;

            // 두 번째 선거구 연결성 체크
            if (!isConnected(false)) return;

            // 인구 차이 계산 및 최솟값 갱신
            int diff = Math.abs(curPopulation - (TotalPopulation - curPopulation));
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            combinations(depth + 1, i + 1, r, curPopulation + peoples[i]);
            visited[i] = false;
        }
    }

    static boolean isConnected(boolean isFirst) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] checked = new boolean[N];

        // 시작점 찾기
        int start = -1;
        for (int i = 0; i < N; i++) {
            if (visited[i] == isFirst) {
                start = i;
                break;
            }
        }
        if (start == -1) return false;

        // BFS 연결성 여부 확인
        queue.offer(start);
        checked[start] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : map[cur]) {
                if (!checked[next] && visited[next] == isFirst) {
                    checked[next] = true;
                    queue.offer(next);
                    cnt++;
                }
            }
        }

        // 같은 선거구로 선택된 모든 구역이 연결되어 있는지 확인
        int totalCnt = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] == isFirst) totalCnt++;
        }

        return cnt == totalCnt;
    }
}