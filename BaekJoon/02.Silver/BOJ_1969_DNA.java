import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] dna;
    static final char[] NUCLEOTIDES = {'A', 'C', 'G', 'T'};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dna = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                dna[i][j] = s.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int[] count = new int[4];  // 0 A / 1 C / 2 G / 3 T
            for (int j = 0; j < N; j++) {
                switch (dna[j][i]) {
                    case 'A': count[0]++;
                        break;
                    case 'C': count[1]++;
                        break;
                    case 'G': count[2]++;
                        break;
                    case 'T': count[3]++;
                        break;
                }
            }

            int frequency = 0;
            char s = ' ';
            for (int j = 0; j < 4; j++) {
                if (count[j] > frequency) {
                    frequency = count[j];
                    s = NUCLEOTIDES[j];
                }  // 동일할 경우 패스
            }
            sb.append(s);
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (sb.charAt(j) != dna[i][j]) sum++;
            }
        }

        System.out.println(sb.toString());
        System.out.println(sum);
    }
}