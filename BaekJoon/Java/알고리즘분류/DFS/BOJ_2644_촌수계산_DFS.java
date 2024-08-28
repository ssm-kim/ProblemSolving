import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, m, answer;
	static ArrayList<Integer>[] map;
	static boolean[] visited;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		
		n = sc.nextInt();       // 전체 사람 수
		int p1 = sc.nextInt();  // 탐색할 부모
		int p2 = sc.nextInt();  // 탐색할 자식
		m = sc.nextInt();       // 부모 자식들 간의 관계
		
		map = new ArrayList[n + 1];  // 리스트 초기화
		for (int i=1; i<=n; i++) {
			map[i] = new ArrayList<>();
		}
		
		for (int i=0; i<m; i++) {  // 양방향 연결
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			map[v1].add(v2);
			map[v2].add(v1);
		}
		
//		for (ArrayList<Integer> x : map) {
//			System.out.println(x);
//		}
		
		answer = -1;
		visited = new boolean[n + 1];
		dfs(p1, p2, 0);
		System.out.println(answer);
	}
	
	static void dfs(int cur_v, int end, int cnt) {
		if (cur_v == end) {
			answer = cnt;
			return;
		}
		
		visited[cur_v] = true;
		for (int next_v : map[cur_v]) {
			if (!visited[next_v]) {
				dfs(next_v, end, cnt + 1);
			}
		}
	}
}