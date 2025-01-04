import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int bitset = 0, x;
        int m = sc.nextInt();  // 연산의 수
        sc.nextLine();

        for (int i = 0; i < m; i++) {
            String operator = sc.next();

            switch (operator) {
                case "add":
                    x = sc.nextInt();
                    // (x - 1) 비트의 인덱스가 0부터 시작하므로 1을 빼줌
                    bitset |= 1 << (x - 1);  // x번째 비트를 1로 설정 (OR 연산)
                    break;

                case "check":
                    x = sc.nextInt();
                    int val = (bitset & 1 << (x - 1)) != 0 ? 1 : 0;  // x번째 비트가 1이면 1, 아니면 0 출력
                    System.out.println(val);
                    break;

                case "remove":
                    x = sc.nextInt();
                    bitset = bitset & ~(1 << (x - 1));  // x번째 비트만 0으로, 나머지는 그대로 유지
                    break;

                case "toggle":
                    x = sc.nextInt();
                    bitset ^= 1 << (x - 1);  // x번째 비트 반전 (XOR 연산)
                    break;

                case "all":
                    bitset |= (~0);  // 모든 비트를 1로 설정
                    break;

                case "empty":
                    bitset &= 0;  // 모든 비트를 0으로 설정
                    break;
            }
        }
    }
}