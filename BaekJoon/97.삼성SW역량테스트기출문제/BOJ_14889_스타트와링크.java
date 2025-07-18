import java.io.*;
import java.util.*;

public class Main {

    static int n, minValue;
    static int[][] map;
    static ArrayList<Integer> startTeam = new ArrayList<>(), linkTeam = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minValue = Integer.MAX_VALUE;
        combinations(0, 0);  // n명 중 n/2명을 선택하는 조합 시작
        System.out.println(minValue);
    }

    static void combinations(int depth, int start) {
        if (depth == n / 2) {  // 스타트 팀 완성
            // 나머지 인원은 링크 팀으로 배정
            for (int i = 0; i < n; i++) {
                if (!startTeam.contains(i)) {
                    linkTeam.add(i);
                }
            }

            // 각 팀의 시너지 능력치 계산
            int startSum = 0;
            int linkSum = 0;
            for (int i = 0; i < n / 2; i++) {
                for (int j = i + 1; j < n / 2; j++) {
                    int startX = startTeam.get(i);
                    int startY = startTeam.get(j);
                    startSum += map[startX][startY];
                    startSum += map[startY][startX];  // 양방향 시너지

                    int linkX = linkTeam.get(i);
                    int linkY = linkTeam.get(j);
                    linkSum += map[linkX][linkY];
                    linkSum += map[linkY][linkX];  // 양방향 시너지
                }
            }

            // 스타트 팀과 링크 팀의 능력치의 차이의 최솟값 갱신
            minValue = Math.min(minValue, Math.abs(startSum - linkSum));

            // 링크팀 초기화
            linkTeam.clear();
            return;
        }

        for (int i = start; i < n; i++) {
            startTeam.add(i);
            combinations(depth + 1, i + 1);
            startTeam.remove(startTeam.size() - 1);  // 백트래킹
        }
    }
}

//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//
//    static int answer = Integer.MAX_VALUE;
//    static int N;
//    static int[][] board;
//    static boolean[] visited;
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        board = new int[N][N];
//
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                board[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        visited = new boolean[N];
//        combinations(0, 0);
//
//        System.out.println(answer);
//    }
//
//    static void combinations(int depth, int start) {
//        if (depth == N / 2) {
//
//            int startTeam = 0;
//            int linkTeam = 0;
//
//            // 모든 사람 쌍에 대해 시너지 계산
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (i == j) continue;  // 자기 자신은 제외
//
//                    // visited가 true인 사람들은 스타트팀
//                    if (visited[i] && visited[j]) {
//                        startTeam += board[i][j];
//                    }
//
//                    // visited가 false인 사람들은 링크팀
//                    if (!visited[i] && !visited[j]) {
//                        linkTeam += board[i][j];
//                    }
//                }
//            }
//            answer = Math.min(answer, Math.abs(startTeam - linkTeam));
//            return;
//        }
//
//        for (int i = start; i < N; i++) {
//            visited[i] = true;
//            combinations(depth + 1, i + 1);
//            visited[i] = false;
//        }
//    }
//}