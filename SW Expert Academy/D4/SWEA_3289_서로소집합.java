import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n,m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            make();
            sb.append("#" + tc + " ");

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == 0) {
                    union(a, b);
                } else if (cmd == 1) {
                    if (findSet(a) == findSet(b)) {
                        sb.append(1);
                    }
                    else {
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void make() {  // make : 유일한 멤버 x를 포함하는 새로운 집합을 생성하는 연산
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;  // 자신의 부모를 자신으로하여 대표자가 되도록 설정
        }
    }

    static int findSet(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return;

        parents[bRoot] = aRoot;
    }
}