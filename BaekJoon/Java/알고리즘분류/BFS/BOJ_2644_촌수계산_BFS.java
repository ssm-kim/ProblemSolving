import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int N;
    static int[][] map;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            N = sc.nextInt();
            int V = sc.nextInt();

            map = new int[101][101];
            visited = new int[101];

            for (int i = 0; i < N / 2; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map[from][to] = 1;  // 단방향 그래프
            }

            int answer = bfs(V);

            System.out.println("#" + tc + " " + answer);
        }
    }
    private static int bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);

        int depth = 1;
        visited[v] = depth;

        while(!queue.isEmpty()){
            v = queue.poll();

            for(int i=0;i<=100;i++){
                if(map[v][i]==1 && visited[i]==0){
                    queue.offer(i);
                    visited[i] = visited[v]+1;
                }
            }

            depth = Math.max(depth,visited[v]);
        }

        for(int i=100;i>=0;i--){
            if(visited[i]==depth){
                return i;
            }
        }
        return -1;
    }
}