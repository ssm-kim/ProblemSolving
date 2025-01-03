import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static int[] parents;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parents = new int[N + 1];  // 부모 정의
            rank = new int[N + 1];     // 랭크 정의
            for (int i = 0; i <= N; i++) {
                parents[i] = i;  // 부모 초기화
            }

            System.out.print("#" + tc + " ");
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op == 0) {
                    union(a, b);
                }
                else if (op == 1) {
                    if (find(a) == find(b)) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * 최적화는 둘 중 하나만 해도 된다. ( 자바는 경로 단축이 코드가 간단 )
     * 1. find 최적화 (경로 단축)
     * 특정 원소의 루트 노드를 찾는 연산
     * 경로 압축: 찾으면서 만난 모든 원소들이 직접 루트를 가리키도록 설정
     */
    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }  // 만약에 스스로를 부모로 칭한다면  (자신이 루트면 반환)
        parents[a] = find(parents[a]);  // 경로 단축
        return parents[a];
    }

    /**
     * 2. union 최적화 (랭크, 최대 높이를 확인해서 합쳐준다.)
     * 두 집합을 합치는 연산
     * a의 부모를 b로 만든다.
     */
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;  // 부모가 같다면 종료.

        parents[aRoot] = bRoot;
    }

    //    랭크 최적화
//    static void union(int a, int b) {
//        a = find(a);
//        b = find(b);
//
//        if (a == b) return;
//
//        if (rank[a] < rank[b]) {
//            parents[a] = b;
//        } else if (rank[a] > rank[b]){
//            parents[b] = a;
//        } else {
//            parents[a] = b;  // 반대로 해도 상관 없음.
//            rank[b]++;
//        }
//    }  // 두 집합을 합친다.
}
