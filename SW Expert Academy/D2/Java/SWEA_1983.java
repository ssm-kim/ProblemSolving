package D2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class SWEA_1983 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();  // 총 n명의 학생
            int k = sc.nextInt();  // 학점을 알고 싶은 학생 번호
            Double [] totalScores = new Double[n];

            for (int i = 0; i < n; i++) {
                int midScore = sc.nextInt();  // 중간
                int finalScore = sc.nextInt();  // 기말
                int assignScore = sc.nextInt();  // 과제
                double sum = midScore * 0.35 + finalScore * 0.45 + assignScore * 0.2;  // 점수 산출
                totalScores[i] = sum;
            }

            double target = totalScores[k-1];  // 찾아야 하는 학생 총 점수

            Arrays.sort(totalScores, Collections.reverseOrder());  // 역순 정렬
            int targetIdx = Arrays.asList(totalScores).indexOf(target);  // target 인덱스 값 추출

            System.out.print("#" + (tc+1) + " ");
            int idx = 0;
            for (int i = 0; i < grade.length; i++) {
                for (int j = 0; j < n / 10; j++) {
                    if (idx == targetIdx) {  // target 인덱스와 같다면 학점 출력
                        System.out.println(grade[i]);
                    }
                    idx += 1;
                }
            }
        }
    }
}
