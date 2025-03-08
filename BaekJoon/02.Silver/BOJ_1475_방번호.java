import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, Integer> count = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String room = br.readLine();
        
        // 0-9까지 숫자 빈도수 초기화
        for (int i = 0; i <= 9; i++) {
            count.put(i, 0);
        }
        
        // 각 숫자 개수 카운트
        for (int i = 0; i < room.length(); i++) {
            int num = room.charAt(i) - '0';
            count.put(num, count.get(num) + 1);
        }
        
        // 6과 9는 서로 뒤집어 사용 가능
        int sixNineTotal = count.get(6) + count.get(9);
        count.put(6, sixNineTotal / 2);
        count.put(9, sixNineTotal / 2);

        if (sixNineTotal % 2 != 0) {
            count.put(6, count.get(6) + 1);
        }  // 나머지가 있다면 6 또는 9에 +1 해줌

        // 필요한 최대 세트 수 계산
        int maxCount = 0;
        for (int value : count.values()) {
            maxCount = Math.max(maxCount, value);
        }
        System.out.println(maxCount);
    }
}