import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N, M, answer;
    static ArrayList<Integer>[] map;  // 각 학생별 연결 관계를 저장할 인접 리스트
    static boolean[] visited;         // 조 편성 여부 체크 배열

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new ArrayList[N + 1];
            visited = new boolean[N + 1];
            for (int i = 0; i <= N; i++) map[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 양방향 연결 (같은 조가 되고 싶은 학생들 서로 연결)
                map[a].add(b);
                map[b].add(a);
            }

            answer = 0;
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;  // 이미 조가 있는 학생은 건너뜀
                dfs(i);                    // 현재 학생과 연결된 모든 학생들 같은 조로 편성
                answer++;                  // 새로운 조 하나가 완성
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int cur) {
        visited[cur] = true;  // 현재 학생을 조에 편성했다고 표시

        for (int next : map[cur]) {
            if (!visited[next]) {  // 아직 조가 없는 학생이면
                dfs(next);         // 같은 조로 편성
            }
        }  // 현재 학생과 연결된 다른 학생들 확인
    }
}
