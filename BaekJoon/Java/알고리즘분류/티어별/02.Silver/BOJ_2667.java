package algoClassification.BFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class BOJ_2667 {

    static int n;
    static int[][] arr;
    static boolean[][] visit;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            ArrayList<String> tmp = new ArrayList<>(Arrays.asList(sc.next().split("")));
            for (int j = 0; j < tmp.size(); j++) {
                arr[i][j] = Integer.parseInt(tmp.get(j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 1이면서 방문하지 않았다면 bfs 실행
                if (arr[i][j] == 1 && !visit[i][j]) {
                    int cnt = bfs(new int[] { i, j });
                    // 값 반환 후 ans에 삽입
                    ans.add(cnt);
                }
            }
        }
        Collections.sort(ans);  // 오름차순 정렬
        System.out.println(ans.size());
        for (int i : ans) {
            System.out.println(i);
        }
    }

    static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>(); // 좌표를 담으려면 배열로 받아야 함.
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };
        int cnt = 1;        // 연결되어 있는 집의 수
        queue.offer(start); // 0번 인덱스부터 시작

        while (!queue.isEmpty()) { // 큐가 비어있지 않다면 (방문 해야될 방이 존재함.)
            int[] pos = queue.poll();
            int cx = pos[0], cy = pos[1]; // 현재 x, y 좌표
            visit[cx][cy] = true; // 현재 좌표 방 방문

            for (int i = 0; i < 4; i++) {  // 4방향 탐색
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                // 범위 포함 여부 확인, 집인지 확인, 방문 여부 확인
                if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] == 1 && !visit[nx][ny]) {
                    queue.offer(new int[] {nx, ny});
                    visit[nx][ny] = true;
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
}