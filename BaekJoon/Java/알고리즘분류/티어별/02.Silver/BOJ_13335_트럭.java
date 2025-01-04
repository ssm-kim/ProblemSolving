import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, W, L;
    static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 트럭 갯수
        W = Integer.parseInt(st.nextToken());  // 다리의 길이
        L = Integer.parseInt(st.nextToken());  // 다리 최대 하중
        int[] trucks = new int[N];  // 각 트럭의 무게

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < W; i++) {
            queue.offer(0);
        }  // 큐에 다리 길이 만큼 0을 채운다.

        int weightOnBridge = 0;  // 현재 다리에 있는 총 무게
        int time = 0;
        int truckIdx = 0;
        while (!queue.isEmpty()) {
            time++;

            weightOnBridge -= queue.poll(); // 다리 위에 있는 총 무게 갱신 및 다리에서 트럭 내리기

            if (truckIdx < N) {  // 새로운 트럭을 올릴 수 있는지 체크
                if (weightOnBridge + trucks[truckIdx] <= L) {
                    queue.offer(trucks[truckIdx]);       // 트럭을 다리에 올림
                    weightOnBridge += trucks[truckIdx];  // 다리 총 무게 업데이트
                    truckIdx++; // 다음 트럭으로 이동
                }
                else {
                    queue.offer(0);  // 트럭을 올릴 수 없으면 0을 다리에 추가
                }
            }
        }
        System.out.println(time);
    }
}
