import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] dx = {1, -1, 2};
    static boolean[] visited = new boolean[100001];  // 최대 위치가 100,000이므로 고정 크기 사용

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n));
    }

    static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int nx = pos[0];
            int time = pos[1];

            if (nx == k) {    // 목적지 도착
                return time;  // 현재 시간 반환
            }

            for (int i = 0; i < 3; i++) {
                int next_pos = nx + dx[i];
                if (i == 2) {  // 순간이동 처리
                    next_pos = nx * dx[i];
                }

                if (next_pos < 0 || next_pos > 100000) {
                    continue;
                }

                // 범위를 벗어나지 않고, 방문하지 않았다면
                if (!visited[next_pos]) {
                    visited[next_pos] = true;
                    queue.offer(new int[]{next_pos, time + 1});  // 다음 위치와 증가된 시간을 큐에 삽입
                }
            }
        }
        return -1; // 도달할 수 없는 경우 (실제로 발생하지 않음)
    }
}