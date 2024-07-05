package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SWEA_1974 {

            public static void main(String[] args) throws FileNotFoundException {
                System.setIn(new FileInputStream("SWEA/src/input.txt"));
                Scanner sc = new Scanner(System.in);
                int T = sc.nextInt();
                int [][] sudoku = new int[9][9];

                for (int tc = 0; tc < T; tc++) {
                    int state = 1;
                    Set<Integer> row = new HashSet<>();  // 행 중복값 제거
                    Set<Integer> col = new HashSet<>();  // 열 중복값 제거
                    Set<Integer> diagonal = new HashSet<>();  // 대각 중복값 제거
            int rIdx = 0, cIdx = 0;

            for (int i = 0; i < 9; i++) {  // 입력값 세팅
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < 9; i++) {  // 입력값 세팅
                for (int j = 0; j < 9; j++) {
                    if (!row.contains(sudoku[i][j])) {  // 행 체크
                        row.add(sudoku[i][j]);
                    }
                    if (!col.contains(sudoku[j][i])) {  // 열 체크
                        col.add(sudoku[j][i]);
                    }
                }

                if (i != 0 && i % 3 == 0) {  // 3*3 인덱스 값 변경
                    rIdx += 3;
                    cIdx = 0;
                }

                for (int k = rIdx; k < rIdx+3; k++) {  // 3*3 체크
                    for (int l = cIdx; l < cIdx+3; l++) {
                        if (!diagonal.contains(sudoku[k][l])) {
                            diagonal.add(sudoku[k][l]);
                        }
                    }
                }
                cIdx += 3;

                if (!checkSudoku(row, col, diagonal)) {  // 행, 열, 대각이 스도쿠가 맞는지 체크
                    state = 0;
                    break;
                }
                row.clear();  // 해쉬 셋 사용 후 빈 값으로 초기화 필수
                col.clear();
                diagonal.clear();
            }
            System.out.printf("#%d %d\n", tc+1, state);
        }
    }

    public static boolean checkSudoku(Set<Integer> row, Set<Integer> col, Set<Integer> diagonal) {
        if (row.size() != 9 || col.size() != 9 || diagonal.size() != 9) {
            return false;
        }
        return true;
    }
}
