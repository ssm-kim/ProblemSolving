import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static int[][] noteBook;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        noteBook = new int[n][m];

        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            // 각 스티커 모양
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int stickerCnt = 0;
            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < c; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                    if (sticker[j][l] == 1) stickerCnt++;
                }
            }

            // 스티커를 노트북에 붙이기.
            if (!tryAttach(r, c, stickerCnt, sticker)) {
                // 못 붙이면 90도 회전 3방향 시도 후 다시 시도
                for (int dir = 1; dir < 4; dir++) {
                    int[][] rotatedSticker = rotate(r, c, dir, sticker);
                    if (dir == 1 || dir == 3) {
                        if (tryAttach(c, r, stickerCnt, rotatedSticker)) {
                            break;
                        }
                    } else {
                        if (tryAttach(r, c, stickerCnt, rotatedSticker)) {
                            break;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (noteBook[i][j] == 1) answer++;
            }
        }
        System.out.println(answer);
    }

    static int[][] rotate(int r, int c, int dir, int[][] sticker) {
        if (dir == 1) { // 오른쪽 90도
            int nR = c;
            int nC = r;
            int[][] nSticker = new int[nR][nC];

            for (int i = 0; i < nR; i++) { // 5
                for (int j = 0; j < nC; j++) { // 2
                    nSticker[i][j] = sticker[r - j - 1][i];
                }
            }
            sticker = nSticker;
        }
        else if (dir == 2) { // 오른쪽 180도
            int[][] nSticker = new int[r][c];
            for (int i = 0; i < r; i++) { // 2
                for (int j = 0; j < c; j++) {  // 5
                    nSticker[i][j] = sticker[r - i - 1][c - j - 1];
                }
            }
            sticker = nSticker;
        }
        else {  // 오른쪽 270도
            int nR = c;
            int nC = r;
            int[][] nSticker = new int[nR][nC];

            for (int i = 0; i < nR; i++) { // 5
                for (int j = 0; j < nC; j++) { // 2
                    nSticker[i][j] = sticker[j][c - i - 1];
                }
            }
            sticker = nSticker;
        }
        return sticker;
    }

    static boolean tryAttach(int r, int c, int stickerCnt, int[][] sticker) {
        ArrayList<int[]> path = new ArrayList<>();
        for (int i = 0; i < n - r + 1; i++) {
            for (int j = 0; j < m - c + 1; j++) {
                path.clear();
                for (int l = 0; l < r; l++) {
                    for (int o = 0; o < c; o++) {
                        if (noteBook[i + l][j + o] == 0 && sticker[l][o] == 1) {
                            path.add(new int[] {i + l, j + o});
                        }
                    }
                }
                // 노트북 스티커 붙이기 (순서는 왼에서 오 고정이므로 붙일 수 있을 때 바로 붙임.)
                if (stickerCnt == path.size()) {
                    for (int[] p : path) {
                        noteBook[p[0]][p[1]] = 1;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}