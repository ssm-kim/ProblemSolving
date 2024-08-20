import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 설탕의 양
		int three = 0, five = 0;
		int answer = 0; // 총 몇개의 봉지가 들어가는지

		// 5로 나눈 나머지 값이 0이 아니고 3이상이면
		while (n % 5 != 0 && n >= 3) {
			three++; // 3kg 증가
			n -= 3;
		}

		// 5로 나눠떨어진다면
		if (n % 5 == 0) {
			five = n / 5;
			answer = three + five; 
		} else {
			answer = -1;
		}

		System.out.println(answer);
	}
}