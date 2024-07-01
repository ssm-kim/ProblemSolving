package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_1288 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();  // 입력값 n 고정
            int curNum = 1;        // 곱하는 숫자
            int cnt = 0;           // 총 양을 세는 횟수
            ArrayList <Integer> lst = new ArrayList<>();
            while (lst.size() < 10) {
                int tmp = curNum * n;
                int[] intArr = intToArray(tmp);

                for (int i : intArr) {
                    if (lst.contains(i) == false) {
                        lst.add(i);
                    }
                }
                curNum += 1;
                cnt += 1;
            }
            System.out.printf("#%d %d\n", tc+1, n*cnt);
        }
    }
    public static int[] intToArray(int tmp) {
        // 숫자를 문자열로 변환
        String strNum = Integer.toString(tmp);

        // 문자열 길이만큼의 배열 생성
        int[] answer = new int[strNum.length()];

        // 각 문자를 숫자로 변환하여 배열에 저장
        for (int i = 0; i < strNum.length(); i++) {
            answer[i] = strNum.charAt(i) - '0';
        }
        return answer;
    }
}