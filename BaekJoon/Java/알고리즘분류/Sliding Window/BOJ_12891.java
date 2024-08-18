import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // 최소 요구 개수와 현재 윈도우 내 문자의 개수를 저장하는 배열
    static int[] minCount = new int[4];
    static int[] count = new int[4];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt();
        int P = sc.nextInt();
        sc.nextLine();

        char[] dna = sc.nextLine().toCharArray();

        for (int i = 0; i < 4; i++) {  // 최소 요구 개수 입력 받기
            minCount[i] = sc.nextInt();
        }

        int answer = 0; // 유효한 윈도우의 개수

        for (int i = 0; i < P; i++) {  // 초기 윈도우 설정
            addChar(dna[i]);
        }

        if (check()) answer++;  // 초기 윈도우가 조건을 만족하는지 확인

        for (int i = P; i < S; i++) {  // 슬라이딩 윈도우 기법을 사용하여 나머지 부분 처리
            int j = i - P;
            removeChar(dna[j]);
            addChar(dna[i]);
            System.out.println(Arrays.toString(count));
            if (check()) answer++;
        }

        System.out.println(answer);
    }

    static boolean check() {   // 현재 윈도우가 최소 요구 개수를 만족하는지 확인
        for (int i = 0; i < 4; i++) {
            if (count[i] < minCount[i]) {
                return false; // 하나라도 부족하면 false 반환
            }
        }
        return true; // 모든 조건을 만족하면 true 반환
    }

    static void addChar(char c) {  // 문자 추가 함수 (각 문자의 개수 증가)
        switch (c) {
            case 'A': count[0]++; break;
            case 'C': count[1]++; break;
            case 'G': count[2]++; break;
            case 'T': count[3]++; break;
        }
    }

    static void removeChar(char c) {  // 문자 제거 함수 (각 문자의 개수 감소)
        switch (c) {
            case 'A': count[0]--; break;
            case 'C': count[1]--; break;
            case 'G': count[2]--; break;
            case 'T': count[3]--; break;
        }
    }
}
