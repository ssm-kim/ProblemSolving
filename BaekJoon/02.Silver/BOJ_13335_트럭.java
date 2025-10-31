import java.io.*;
import java.util.*;

// 트럭 시뮬레이션 코드 -> 내 풀이

class Truck {
    int weight, time;

    public Truck(int weight, int time) {
        this.weight = weight;
        this.time = time;
    }
}

public class Main {

    static int n, w, l;
    static Queue<Integer> truckWeight = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 트럭의 수
        w = Integer.parseInt(st.nextToken());  // 다리 길이
        l = Integer.parseInt(st.nextToken());  // 다리 최대하중

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truckWeight.offer(Integer.parseInt(st.nextToken()));  // 트럭 무게 입력
        }
        System.out.println(simulate());
    }

    static int simulate() {
        LinkedList<Truck> bridge = new LinkedList<>();  // 다리 위 트럭 상태 저장
        int timer = 0, bridgeOnWeight = 0;              // 경과 시간, 현재 다리 위 총 하중

        while (true) {
            // 모든 트럭이 건넜다면 종료
            if (truckWeight.isEmpty() && bridge.isEmpty()) break;

            timer++;  // 1초 경과

            // 1. 다리 위 트럭 이동 및 하차 처리
            ArrayList<Integer> rmIndex = new ArrayList<>();
            for (int i = 0; i < bridge.size(); i++) {
                Truck truck = bridge.get(i);

                if (truck.time + 1 > w) {      // 다리 끝 도달 -> 하차 예정
                    rmIndex.add(i);
                } else {                       // 아직 다리 위 -> 한 칸 전진
                    bridge.set(i, new Truck(truck.weight, truck.time + 1));
                }
            }

            for (int i = rmIndex.size() - 1; i >= 0; i--) {  // 뒤에서부터 제거
                int idx = rmIndex.get(i);
                Truck rmTruck = bridge.get(idx);
                bridgeOnWeight -= rmTruck.weight;  // 제거되는 트럭은 전체 하중에서 감소
                bridge.remove(idx);
            }

            // 2. 새 트럭 진입 가능하면 진입
            int target = truckWeight.isEmpty() ? -1 : truckWeight.peek();
            if (target != -1 && bridgeOnWeight + target <= l) {
                bridge.offer(new Truck(target, 1));  // 다리에 진입
                bridgeOnWeight += target;                 // 하중 추가
                truckWeight.poll();                       // 대기열 제거
            }
        }
        return timer;
    }
}

/*

큐 특성을 활용한 코드 -> 다른 풀이

import java.io.*;
import java.util.*;

class Truck {
    int weight;
    public Truck(int weight) {
        this.weight = weight;
    }
}

public class Main {

    static int n, w, l;
    static Queue<Integer> waiting = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 트럭 수
        w = Integer.parseInt(st.nextToken());  // 다리 길이
        l = Integer.parseInt(st.nextToken());  // 다리 최대하중

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) waiting.offer(Integer.parseInt(st.nextToken()));

        System.out.println(simulate());
    }

    static int simulate() {
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위 상태 큐 (트럭 무게 or 0)
        for (int i = 0; i < w; i++) bridge.offer(0); // 다리 길이만큼 0으로 초기화

        int timer = 0;
        int bridgeWeight = 0; // 현재 다리 위 총 무게

        // 1. 모든 트럭이 다리를 건널 때까지 반복
        while (!bridge.isEmpty()) {
            timer++;

            // 2. 다리 앞 칸에서 트럭(또는 빈 칸) 내리기
            bridgeWeight -= bridge.poll();

            // 3. 새 트럭 진입 여부 확인
            if (!waiting.isEmpty()) {
                int nextTruck = waiting.peek();
                if (bridgeWeight + nextTruck <= l) {  // 다리 하중 가능
                    bridge.offer(nextTruck);          // 트럭 진입
                    bridgeWeight += nextTruck;
                    waiting.poll();                   // 대기열에서 제거
                } else {
                    bridge.offer(0);                  // 진입 불가 → 빈 칸 추가
                }
            }
        }
        return timer;
    }
}


*/