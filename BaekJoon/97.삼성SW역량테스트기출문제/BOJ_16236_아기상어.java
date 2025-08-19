import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BabyShark {
    int x, y, size, eatenCnt;

    public BabyShark(int x, int y, int size, int eatenCnt) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eatenCnt = eatenCnt;
    }
}

public class Main {

    static BabyShark babyShark;
    static int n;
    static int minDistance;

    // 상 좌 하 우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    babyShark = new BabyShark(i, j, 2, 0);
                    map[i][j] = 0;  // 상어 위치를 빈 칸으로 초기화
                }
            }
        }

        int seconds = 0;
        while (true) {
            // BFS로 먹을 수 있는 물고기들 찾기
            ArrayList<int[]> eatenPos = eatenFishCheck(babyShark.x, babyShark.y);

            // 먹을 수 있는 물고기가 없으면 종료
            if (eatenPos.isEmpty()) {
                break;
            }
            // 물고기가 1마리면 바로 먹기
            else if (eatenPos.size() == 1) {
                int[] pos = eatenPos.get(0);
                eatenFish(pos);
                seconds += pos[2];
            }
            // 여러 마리면 우선순위에 따라 선택 - 1. 가장 위, 2. 가장 왼쪽
            else {
                // 가장 위에 있는 물고기들 찾기
                int targetX = Integer.MAX_VALUE;
                for (int[] pos : eatenPos) {
                    targetX = Math.min(targetX, pos[0]);
                }

                ArrayList<int[]> onTop = new ArrayList<>();
                int targetY = Integer.MAX_VALUE;
                for (int[] pos : eatenPos) {
                    if (targetX == pos[0]) {
                        onTop.add(new int[] {pos[0], pos[1], pos[2]});
                        targetY = Math.min(targetY, pos[1]);  // 가장 왼쪽 좌표
                    }
                }

                if (onTop.size() == 1) {
                    int[] pos = onTop.get(0);
                    eatenFish(pos);
                    seconds += pos[2];
                }
                // 가장 위에 여러 마리면 가장 왼쪽 물고기 선택
                else {
                    for (int[] pos : onTop) {
                        if (targetX == pos[0] && targetY == pos[1]) {
                            eatenFish(pos);
                            seconds += pos[2];
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(seconds);
    }

    // 물고기를 먹고 상어 위치 이동 및 크기 증가 처리
    static void eatenFish(int[] pos) {
        map[babyShark.x][babyShark.y] = 0;  // 기존 상어 위치 제거
        int nx = pos[0];
        int ny = pos[1];

        map[nx][ny] = 9;  // 새 위치에 상어 배치

        // 크기만큼 먹으면 크기 증가, 아니면 먹은 개수만 증가
        if (babyShark.size == babyShark.eatenCnt + 1) {
            babyShark = new BabyShark(nx, ny, babyShark.size + 1, 0);
        } else {
            babyShark = new BabyShark(nx, ny, babyShark.size, babyShark.eatenCnt + 1);
        }
    }

    // BFS로 먹을 수 있는 물고기들을 최단거리 순으로 찾기
    static ArrayList<int[]> eatenFishCheck(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> eatenFish = new ArrayList<>();
        queue.offer(new int[] {sx, sy, 0});

        visited = new boolean[n][n];
        visited[sx][sy] = true;

        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int distance = pos[2];

            if (distance > minDist) continue;  // 이미 더 먼 거리면 탐색 중단

            // 먹을 수 있는 물고기 발견
            if ( map[cx][cy] != 0 &&  // 빈 칸이 아니고
                map[cx][cy] != 9 &&  // 자기 위치가 아니고
                map[cx][cy] < babyShark.size) {
                minDist = Math.min(minDist, distance);
                eatenFish.add(new int[] {cx, cy, distance});
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위, 방문, 큰 물고기 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] > babyShark.size) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny, distance + 1});
            }
        }

        // 최단거리 물고기들만 반환
        ArrayList<int[]> filteredFish = new ArrayList<>();
        for (int[] fish : eatenFish) {
            if (minDist == fish[2]) {
                filteredFish.add(new int[] {fish[0], fish[1], minDist});
            }
        }

        return filteredFish;
    }
}