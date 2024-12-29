import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] map = new int[100001];
    static int[] dx = {1, -1, 2};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map[N] = 1;  // 수빈이 위치
        map[K] = 2;  // 동생 위치

        bfs(N);
        System.out.println(answer);
    }

    private static void bfs(int sx) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, 0});  // [현재 위치, 소요 시간] 저장
        map[sx] = 3;  // 시작 위치 방문 검사

        int nx;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int curTime = pos[1];

            for (int i = 0; i < 3; i++) {
                nx = (i == 2) ? cx * dx[i] : cx + dx[i];

                if (nx < 0 || nx >= 100001) continue;  // 범위 검사

                if (map[nx] == 2) {
                    answer = curTime + 1;
                    return;
                }  // 동생을 찾으면

                if (map[nx] == 0) {
                    map[nx] = 3;  // 방문 체크
                    queue.offer(new int[] {nx, curTime + 1});
                }  // 미방문 위치면 큐에 추가
            }
        }
    }
}

