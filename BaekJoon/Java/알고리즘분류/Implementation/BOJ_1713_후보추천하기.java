import java.io.*;
import java.util.*;

public class Main {

    static int N, recommendCnt;
    static Map<Integer, int[]> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 사진틀 갯수
        recommendCnt = Integer.parseInt(br.readLine());  // 전체 학생의 총 추천 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int time = 0; time < recommendCnt; time++) {
            int student = Integer.parseInt(st.nextToken());

            // 1. 이미 게시된 경우  >  추천수만 증가
            if (map.containsKey(student)) {
                map.get(student)[0]++;
                continue;
            }

            // 2. 사진들이 꽉 찬 경우  >  삭제 후 추가
            if (map.size() >= N) {
                int rmStudent = 0;
                int minRecommend = Integer.MAX_VALUE;
                int oldestTime = Integer.MAX_VALUE;

                // 가장 적은 추천, 가장 오래된 사진 추적
                for (int key : map.keySet()) {
                    int[] info = map.get(key);
                    if (info[0] < minRecommend || (info[0] == minRecommend && info[1] < oldestTime)) {
                        minRecommend = info[0];
                        oldestTime = info[1];
                        rmStudent = key;
                    }
                }
                map.remove(rmStudent);  // 사진 삭제 ( 추천 횟수도 0 초기화 )
            }

            // 3. 새로운 학생 추가
            map.put(student, new int[]{1, time});  // [추천 횟수, 게시 시간]
        }

        // 학생 번호 순으로 정렬해서 출력
        List<Integer> result = new ArrayList<>(map.keySet());
        Collections.sort(result);
        for (int student : result) {
            System.out.print(student + " ");
        }
    }
}