import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Number implements Comparable<Number> {
    int num, showCnt;

    public Number(int num, int showCnt) {
        this.num = num;
        this.showCnt = showCnt;
    }

    @Override
    public int compareTo(Number other) {
        if (this.showCnt == other.showCnt) {
            return Integer.compare(this.num, other.num);
        }
        return Integer.compare(this.showCnt, other.showCnt);
    }
}

public class Main {

    static int r, c, k;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;  // 1-based → 0-based 변환
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        // 초기 세팅
        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int clearTime = -1;
        for (int time = 0; time < 101; time++) {
            // 범위 안에 있을 때만 값 체크
            if (r < board.length && c < board[0].length) {
                if (board[r][c] == k) {
                    clearTime = time;
                    break;
                }
            }
            // 범위 밖이어도 연산은 계속 수행 (배열이 확장될 수 있음)
            int rowCnt = board.length;
            int colCnt = board[0].length;

            if (rowCnt >= colCnt) {
                operatorR(rowCnt, colCnt);
            }
            else {
                operatorC(rowCnt, colCnt);
            }
        }
        System.out.println(clearTime);
    }

    static void operatorR(int row, int col) {
        ArrayList<ArrayList<Number>> result = new ArrayList<>();
        int maxColLength = 0;

        // 각 행별로 처리
        for (int i = 0; i < row; i++) {
            ArrayList<Number> numbers = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            // 각 행에서 0이 아닌 숫자들 카운트
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0) continue;
                map.put(board[i][j], map.getOrDefault(board[i][j], 0) + 1);
            }

            // key -> 숫자, value -> 숫자의 등장 횟수
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                numbers.add(new Number(key, value));
            }
            Collections.sort(numbers);  // 정렬

            maxColLength = Math.max(maxColLength, numbers.size());  // 가장 긴 길의 행을 찾는다. 0으로 치환하기 위해
            result.add(numbers);
        }

        // 모든 행을 가장 긴 행 길이에 맞춰 0으로 패딩
        for (ArrayList<Number> numbers : result) {
            while (numbers.size() < maxColLength) {
                numbers.add(new Number(0, 0));
            }
        }

        // 기존 board를 새로운 배열로 갱신한다.
        int newRow = result.size();
        int newCol = Math.min(100, result.get(0).size() * 2);  // 최대 100으로 제한

        board = new int[newRow][newCol];
        int rowIdx = 0;
        for (ArrayList<Number> numbers : result) {
            int colIdx = 0;
            for (Number number : numbers) {
                board[rowIdx][colIdx++] = number.num;      // 숫자
                board[rowIdx][colIdx++] = number.showCnt;  // 등장횟수
            }
            rowIdx++;
        }
    }

    static void operatorC(int row, int col) {
        ArrayList<ArrayList<Number>> result = new ArrayList<>();
        int maxRowLength = 0;

        // 각 열별로 처리
        for (int j = 0; j < col; j++) {
            ArrayList<Number> numbers = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            // 각 열에서 0이 아닌 숫자들 카운트 (세로로 읽기)
            for (int i = 0; i < row; i++) {
                if (board[i][j] == 0) continue;
                map.put(board[i][j], map.getOrDefault(board[i][j], 0) + 1);
            }

            // key -> 숫자, value -> 숫자의 등장 횟수
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                numbers.add(new Number(key, value));
            }
            Collections.sort(numbers);  // 정렬

            maxRowLength = Math.max(maxRowLength, numbers.size());  // 가장 긴 길의 행을 찾는다. 0으로 치환하기 위해
            result.add(numbers);
        }

        // 모든 열을 가장 긴 열 길이에 맞춰 0으로 패딩
        for (ArrayList<Number> numbers : result) {
            while (numbers.size() < maxRowLength) {
                numbers.add(new Number(0, 0));
            }
        }

        // 기존 board를 새로운 행렬로 갱신한다.
        int newRow = Math.min(100, result.get(0).size() * 2);  // 최대 100으로 제한
        int newCol = result.size();

        board = new int[newRow][newCol];
        for (int j = 0; j < newCol; j++) {
            ArrayList<Number> numbers = result.get(j);
            int rowIdx = 0;
            for (Number number : numbers) {
                board[rowIdx++][j] = number.num;      // 숫자
                board[rowIdx++][j] = number.showCnt;  // 등장횟수
            }
        }
    }
}