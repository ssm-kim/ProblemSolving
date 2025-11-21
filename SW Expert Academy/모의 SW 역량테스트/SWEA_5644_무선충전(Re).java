import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class BCInfo {
    int x, y, coverage, performance;

    public BCInfo(int x, int y, int coverage, int performance) {
        this.x = x;
        this.y = y;
        this.coverage = coverage;
        this.performance = performance;
    }
}

public class Solution {

    static int n = 10;
    static int[] aPath, bPath;
    static int[][] board;  // BC 번호 저장 (중복 시 -1)
    static ArrayList<BCInfo> bcList = new ArrayList<>();
    static HashMap<String, ArrayList<Integer>> duplicationPath = new HashMap<>();  // "x,y" -> BC 번호 리스트

    // 0: 이동X, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            // 초기화
            board = new int[n][n];
            bcList.clear();
            duplicationPath.clear();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());  // 총 이동 시간
            int a = Integer.parseInt(st.nextToken());  // BC의 개수

            // a 사용자 경로
            st = new StringTokenizer(br.readLine());
            aPath = new int[m];
            for (int i = 0; i < m; i++) {
                aPath[i] = Integer.parseInt(st.nextToken());
            }

            // b 사용자 경로
            st = new StringTokenizer(br.readLine());
            bPath = new int[m];
            for (int i = 0; i < m; i++) {
                bPath[i] = Integer.parseInt(st.nextToken());
            }

            // 배터리 충전소 정보 입력
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                bcList.add(new BCInfo(x, y, c, p));
            }

            // 각 BC의 충전 범위를 board에 표시
            for (int i = 0; i < bcList.size(); i++) {
                BCInfo bc = bcList.get(i);
                int bcNum = i + 1;  // BC 번호는 1부터 시작

                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        int distance = Math.abs(bc.x - x) + Math.abs(bc.y - y);

                        if (distance <= bc.coverage) {
                            String key = x + "," + y;

                            if (board[x][y] == 0) {
                                // 처음 충전 가능한 BC
                                board[x][y] = bcNum;
                            } else if (board[x][y] > 0) {
                                // 두 번째 BC (중복 발생)
                                int firstBC = board[x][y];
                                board[x][y] = -1;  // 중복 표시

                                duplicationPath.put(key, new ArrayList<>());
                                duplicationPath.get(key).add(firstBC);
                                duplicationPath.get(key).add(bcNum);
                            } else {
                                // 이미 중복된 곳에 추가 BC
                                duplicationPath.get(key).add(bcNum);
                            }
                        }
                    }
                }
            }

            // 시뮬레이션 시작
            int totalCharge = 0;
            int ax = 0, ay = 0;  // A 시작 위치
            int bx = 9, by = 9;  // B 시작 위치

            // t=0 시점 충전
            totalCharge += getMaxCharge(ax, ay, bx, by);

            // 이동하면서 충전
            for (int i = 0; i < m; i++) {
                // A 이동
                ax += dx[aPath[i]];
                ay += dy[aPath[i]];

                // B 이동
                bx += dx[bPath[i]];
                by += dy[bPath[i]];

                totalCharge += getMaxCharge(ax, ay, bx, by);
            }

            System.out.println("#" + t + " " + totalCharge);
        }
    }

    // 해당 좌표에서 사용 가능한 BC 리스트 가져오기
    static ArrayList<Integer> getAvailableBC(int x, int y) {
        ArrayList<Integer> bcList = new ArrayList<>();
        String key = x + "," + y;

        if (board[x][y] == -1) {
            // 중복된 위치
            bcList = duplicationPath.get(key);
        } else if (board[x][y] > 0) {
            // 단일 BC
            bcList.add(board[x][y]);
        }

        return bcList;
    }

    // 현재 A, B 위치에서 최대 충전량 계산
    static int getMaxCharge(int ax, int ay, int bx, int by) {
        ArrayList<Integer> aBC = getAvailableBC(ax, ay);
        ArrayList<Integer> bBC = getAvailableBC(bx, by);

        int maxCharge = 0;

        // 둘 다 충전 불가능
        if (aBC.isEmpty() && bBC.isEmpty()) {
            return 0;
        }

        // A만 충전 가능
        if (bBC.isEmpty()) {
            for (int bcIdx : aBC) {
                maxCharge = Math.max(maxCharge, bcList.get(bcIdx - 1).performance);
            }
            return maxCharge;
        }

        // B만 충전 가능
        if (aBC.isEmpty()) {
            for (int bcIdx : bBC) {
                maxCharge = Math.max(maxCharge, bcList.get(bcIdx - 1).performance);
            }
            return maxCharge;
        }

        // 둘 다 충전 가능 - 모든 조합 시도
        for (int bcA : aBC) {
            for (int bcB : bBC) {
                int charge;
                if (bcA == bcB) {
                    // 같은 BC 사용 시 한 번만 충전
                    charge = bcList.get(bcA - 1).performance;
                } else {
                    // 다른 BC 사용 시 각각 충전
                    charge = bcList.get(bcA - 1).performance + bcList.get(bcB - 1).performance;
                }
                maxCharge = Math.max(maxCharge, charge);
            }
        }

        return maxCharge;
    }
}