import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

	static int answer = -1;
	static int N, M;
	static int p1, p2;
	static ArrayList<Integer>[] map;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		N = Integer.parseInt(br.readLine());    // 전체 사람 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());  // 촌수 계산 사람 1번
		p2 = Integer.parseInt(st.nextToken());  // 촌수 계산 사람 2번
		M = Integer.parseInt(br.readLine());    // 부모 자식들 간의 관계 갯수
		map = new ArrayList[N + 1];             // 리스트 초기화
		visited = new boolean[N + 1];           // 방문 배열 초기화
		for (int i = 0; i <= N; i++) map[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			map[parent].add(child);  // 양방향
			map[child].add(parent);
		}

		dfs(p1, 0);
		System.out.println(answer);
	}

	static void dfs(int cur_v, int cnt) {
		if (cur_v == p2) {
			answer = cnt;
			return;
		}  // 나와 친척 관계가 있다.

		visited[cur_v] = true;
		for (int next_v : map[cur_v]) {
			if (!visited[next_v]) {
				dfs(next_v, cnt + 1);
			}
		}
	}
}