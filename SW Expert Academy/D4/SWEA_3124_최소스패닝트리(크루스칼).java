import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int V, E;
    static int[] parents;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());  // 정점의 개수
            E = Integer.parseInt(st.nextToken());  // 간선의 개수

            Edge[] edges = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(edges);  // 크루스칼 알고리즘은 가중치별로 오름차순 정렬

            make();
            long cost = 0, check = 0;
            for (Edge edge : edges) {
                if (union(edge.start, edge.end)) {
                    cost += edge.weight;
                    check++;
                }
                if (check == V) break;
            }
            System.out.println("#" + tc + " " + cost);
        }
    }

    static void make() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = -1;  // -1로 초기화
        }
    }

    static int findSet(int a) {
        if (parents[a] < 0) {  // 부모 이면 바로 리턴
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return 1;
        else if (this.weight < o.weight) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Edge{" + "start=" + start + ", end=" + end + ", weight=" + weight + '}';
    }
}