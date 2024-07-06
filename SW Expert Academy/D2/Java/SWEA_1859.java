package D2;

import java.io.*;
import java.util.*;

public class SWEA_1859 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            ArrayList<Integer> buySell = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                buySell.add(Integer.parseInt(str[i]));
            }

            int dayMaxPrice = 0;  // 파는 날의 최대 가격
            // TC가 100만에 최대 매매가가 10000원이다.
            // 따라서 100만 * 10000 -> 최대 100억까지 값이 나올 수 있다.
            // int형은 최대 21억까지 담을 수 있으므로 long 타입 사용
            long profit = 0;      // 전체 이익

            for (int i = buySell.size()-1; i >= 0; i--) {  // for 역순
                int curPrice = buySell.get(i);

                if (dayMaxPrice == 0 || curPrice > dayMaxPrice) {  // 첫째날 -> 가격 저장 또는 현재가격보다 크다면 새로 갱신
                    dayMaxPrice = curPrice;
                } else {
                    if (dayMaxPrice > curPrice) {  // (시장가 > 현재가) -> 이익 추가
                        profit += (dayMaxPrice - curPrice);
                    }
                }
            }
            System.out.println("#" + (tc+1) + " " + profit);
        }
    }
}
