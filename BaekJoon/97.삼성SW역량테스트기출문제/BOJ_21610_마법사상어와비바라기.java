import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;

    // 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // n*n 크기의 맵
        m = Integer.parseInt(st.nextToken());  // 이동 횟수
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최초 시전 시 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
        ArrayList<int[]> cloudPos = new ArrayList<>();  // 현재 구름 좌표
        cloudPos.add(new int[] {n - 1, 0}); cloudPos.add(new int[] {n - 1, 1});
        cloudPos.add(new int[] {n - 2, 0}); cloudPos.add(new int[] {n - 2, 1});

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;  // 이동 방향 인덱스 (0번 인덱스부터 시작)
            int s = Integer.parseInt(st.nextToken());  // 이동 칸 수

            cloudPos = usedSkill(cloudPos, d, s);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    static ArrayList<int[]> usedSkill(ArrayList<int[]> cloudPos, int d, int s) {
        ArrayList<int[]> newCloudPos = new ArrayList<>();
        boolean[][] isCloud = new boolean[n][n];  // 기존 구름이 있던 자리
        // 1. 모든 구름이 d 방향으로 s 칸 이동 및 구름 제거
        for (int[] cloud : cloudPos) {
            int cx = cloud[0];
            int cy = cloud[1];

            for (int i = 0; i < s; i++) {
                cx += dx[d];
                cy += dy[d];

                // 매번 순환 경계선 처리
                if (cx < 0) cx = n - 1;
                if (cx >= n) cx = 0;
                if (cy < 0) cy = n - 1;
                if (cy >= n) cy = 0;

            }
            map[cx][cy]++;  // 구름이 있는 칸에 비가 1씩 내리고 구름이 사라진다.
            newCloudPos.add(new int[] {cx, cy});
            isCloud[cx][cy] = true;
        }

        // 2. 이동했던 좌표마다 대각선 방향 확인 후, 물의 양을 증가시킨다.
        for (int[] cloud : newCloudPos) {
            int cx = cloud[0];
            int cy = cloud[1];
            int waterCnt = 0;  // 물의 양 체크

            for (int i = 1; i < 8; i += 2) {  // 각 대각선 방향 확인.
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 경계 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                // 남아 있는 물의 양이 0보다 크면
                if (map[nx][ny] > 0) {
                    waterCnt++;
                }
            }
            map[cx][cy] += waterCnt;  // 현재 칸에 확인된 물의 양만큼 증가.
        }

        // 3. 구름이 있었던 칸을 제외, 나머지 칸 중에서 물의 양이 2 이상인 칸에 새로운 구름 생성.
        //    단, 구름 생길 시 각 칸의 물의 양이 2만큼 줄어든다.
        ArrayList<int[]> makeCloud = new ArrayList<>();  // 새로운 구름 생성 좌표
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 물의 양이 2이상 + 구름이 없었던 칸이면
                if (map[i][j] >= 2 && !isCloud[i][j]) {
                    map[i][j] -= 2;
                    makeCloud.add(new int[] {i, j});
                }
            }
        }
        return makeCloud;
    }
}