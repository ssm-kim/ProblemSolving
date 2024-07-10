package SBSSol.Lv06_심화1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3003 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> chessPiece = new ArrayList<>();
        int[] origin = {1, 1, 2, 2, 2, 8};  // 기본 체스 갯수

        while (st.hasMoreTokens()) {
            chessPiece.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < origin.length; i++) {
            System.out.print(origin[i] - chessPiece.get(i) + " ");
        }
    }
}
