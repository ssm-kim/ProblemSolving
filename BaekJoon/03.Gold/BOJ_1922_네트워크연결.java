import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    public int compareTo(Edge target) {
        return Integer.compare(this.weight, target.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

public class Main {

    static int N, M;
    static StringTokenizer st;
    static ArrayList<Edge> edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 간선 리스트 초기화
        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        // 모든 간선을 가중치 기준으로 오름차순 정렬
        Collections.sort(edges);

        // 초기에는 모든 노드가 자기 자신을 부모로 가지도록 초기화
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;


        for (Edge edge : edges) {
            System.out.println(edge.toString());
        }

        // 가중치가 작은 간선부터 순회하며 MST 구성
        int answer = 0;  // 총 가중치의 합
        for (Edge edge : edges) {
            // 현재 간선의 양 끝점이 서로 다른 집합에 속해있다면(연결해도 사이클이 생기지 않는다면)
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);  // 두 정점을 같은 집합으로 합침
                answer += edge.weight;        // MST 가중치 합에 현재 간선의 가중치 추가
            }
            System.out.println(Arrays.toString(parents) + " " + edge.weight + " " + answer);
        }
        System.out.println(answer);
    }

    // find: 노드 a가 속한 집합의 대표 노드(루트 노드)를 찾는 메서드
    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }  // 자기 자신이 루트라면 그대로 반환

        // 경로 압축 최적화: 재귀적으로 루트를 찾으면서 만나는 모든 노드들의 부모를 루트 노드로 바로 연결
        return parents[a] = find(parents[a]);
    }

    // union: 두 집합을 하나로 합치는 메서드
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;  // 부모가 같다면

        parents[bRoot] = aRoot;      // b의 집합을 a의 집합에 합침
    }
}