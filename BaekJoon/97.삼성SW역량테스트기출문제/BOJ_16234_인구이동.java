import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, l, r;
    static int curUnionSum;  // 현재 연합 총 인구수
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> curUnion;  // 연합 국가들

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        // 인구 이동이 발생하는 동안 반복
        while (true) {
            ArrayList<ArrayList<int[]>> allUnions = new ArrayList<>();
            ArrayList<Integer> unionSums = new ArrayList<>();
            visited = new boolean[n][n];

            // 모든 칸을 탐색하여 연합 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        curUnion = new ArrayList<>();
                        curUnionSum = 0;

                        // dfs로 연결된 연합 찾기
                        dfs(i, j);

                        allUnions.add(curUnion);
                        unionSums.add(curUnionSum);
                    }
                }
            }

            // 연합이 n*n개 이면, 모든 나라가 독립임.
            if (allUnions.size() == n * n) {
                break;
            }

            days++;  // 연합이 있다면 1일 증가

            // 각 연합별로 인구 재분배 (평균값)
            for (int i = 0; i < allUnions.size(); i++) {
                ArrayList<int[]> union = allUnions.get(i);
                int avgPopulation = unionSums.get(i) / union.size();

                for (int[] coordinate : union) {
                    map[coordinate[0]][coordinate[1]] = avgPopulation;
                }
            }
        }
        System.out.println(days);
    }

    static void dfs(int cx, int cy) {
        visited[cx][cy] = true;
        curUnion.add(new int[] {cx, cy});
        curUnionSum += map[cx][cy];

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

            int diff = Math.abs(map[cx][cy] - map[nx][ny]);

            // 인구 차이가 L 이상 R 이하면 국경선 열고 연합 형성
            if (l <= diff && diff <= r) {
                dfs(nx, ny);
            }
        }
    }
}