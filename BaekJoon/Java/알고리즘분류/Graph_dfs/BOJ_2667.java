import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] visited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int n;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = br.read() - '0';
            }
            br.readLine();
        }

        int homeCnt = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    answer.add(dfs(i, j, 1));  // dfs
                }
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer i : answer) {
            System.out.println(i);
        }
    }

    static int dfs(int cx, int cy, int houseCount) {
        visited[cx][cy] = 1;  // 첫 번째 노드 방문

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 검사
            if (nx < 0 || nx >= n || ny < 0 || ny >= n){
                continue;
            }

            // 이동 여부 체크
            if (map[nx][ny] == 1 && visited[nx][ny] == 0) {
                houseCount = dfs(nx, ny, houseCount + 1);
            }
        }
        return houseCount;
    }
}