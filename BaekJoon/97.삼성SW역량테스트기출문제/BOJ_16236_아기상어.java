// 최적화 버전
import java.io.*;
import java.util.*;


class Shark {
    int x, y, size, distance, eaten;

    public Shark() {

    }
    public Shark(int x, int y, int size, int distance, int eaten) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.distance = distance;
        this.eaten = eaten;
    }
}

public class Main {

    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static Queue<Shark> queue = new LinkedList<>();;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Shark shark = new Shark();

        // 맵 입력 및 아기 상어 위치 찾기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;  // 아기 상어 위치를 빈 칸으로 변경
                    shark = new Shark(i, j, 2, 0, 0);  // 초기 크기 2 고정
                }
            }
        }

        int totalTime = 0;
        while (true) {

            // 먹을 수 있는 물고기들 탐색 (BFS)
            ArrayList<Shark> fishes = fishEatingCount(new ArrayList<>(), shark);

            // 먹을 수 있는 물고기가 없으면 종료
            if (fishes.isEmpty()) {
                totalTime = shark.distance;
                break;
            }
            // 물고기가 1마리인 경우
            else if (fishes.size() == 1) {
                shark = fishes.get(0);
                shark = eatFish(shark);
                map[shark.x][shark.y] = 0;
            }
            // 물고기가 여러 마리인 경우 - 거리, 위쪽, 왼쪽 우선순위로 선택
            else {
                shark = selectClosestFish(fishes);
                shark = eatFish(shark);
                map[shark.x][shark.y] = 0;
            }
        }

        System.out.println(totalTime);
    }

    // 물고기 먹고 크기 증가 로직
    static Shark eatFish(Shark shark) {
        // 자기 크기만큼 먹으면 크기 증가
        if (shark.size == shark.eaten + 1) {
            return new Shark(shark.x, shark.y, shark.size + 1, shark.distance, 0);
        } else {
            return new Shark(shark.x, shark.y, shark.size, shark.distance, shark.eaten + 1);
        }
    }

    // 가장 가까운 물고기 선택 (거리 -> 위쪽 -> 왼쪽 우선순위)
    static Shark selectClosestFish(ArrayList<Shark> fishes) {
        // 이미 가장 가까운 거리의 물고기들만 들어오므로 거리 비교 불필요

        // 1마리면 바로 반환
        if (fishes.size() == 1) {
            return fishes.get(0);
        }

        // 여러 마리면 위쪽, 왼쪽 우선순위로 선택
        // 가장 위쪽 행 찾기
        int topRow = Integer.MAX_VALUE;
        for (Shark fish : fishes) {
            topRow = Math.min(topRow, fish.x);
        }

        // 가장 위쪽 행에서 가장 왼쪽 열 찾기
        int leftCol = Integer.MAX_VALUE;
        for (Shark fish : fishes) {
            if (topRow == fish.x) {
                leftCol = Math.min(leftCol, fish.y);
            }
        }

        // 최종 선택된 물고기 반환
        for (Shark fish : fishes) {
            if (topRow == fish.x && leftCol == fish.y) {
                return fish;
            }
        }

        return fishes.get(0);  // 혹시 모를 경우
    }

    // BFS로 먹을 수 있는 물고기들 찾기
    static ArrayList<Shark> fishEatingCount(ArrayList<Shark> fishes, Shark start) {
        queue.offer(start);
        visited = new boolean[n][n];
        visited[start.x][start.y] = true;
        int minFishDistance = -1;  // 처음 발견한 물고기의 거리

        while (!queue.isEmpty()) {
            Shark current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 및 방문 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 이동 가능한 칸 (빈 칸이거나 내 크기보다 작거나 같은 물고기)
                if (map[nx][ny] == 0 || map[nx][ny] <= current.size) {
                    visited[nx][ny] = true;
                    queue.offer(new Shark(nx, ny, current.size, current.distance + 1, current.eaten));

                    // 먹을 수 있는 물고기 (내 크기보다 작은 물고기)
                    if (map[nx][ny] != 0 && map[nx][ny] < current.size) {
                        int fishDistance = current.distance + 1;

                        // 첫 번째 물고기 발견 시 거리 저장
                        if (minFishDistance == -1) {
                            minFishDistance = fishDistance;
                        }

                        // 같은 거리의 물고기 수집
                        if (fishDistance == minFishDistance) {
                            fishes.add(new Shark(nx, ny, current.size, fishDistance, current.eaten));
                        }
                    }

                    // 더 먼 거리 탐색이 필요한 경우에만 큐에 추카
                    if (minFishDistance == -1 || current.distance + 1 <= minFishDistance) {
                        queue.offer(new Shark(nx, ny, current.size, current.distance + 1, current.eaten));
                    }
                }
            }
        }
        return fishes;
    }
}


// 기존 버전
import java.io.*;
import java.util.*;


class Shark {
    int x, y, size, distance, eaten;

    public Shark() {

    }
    public Shark(int x, int y, int size, int distance, int eaten) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.distance = distance;
        this.eaten = eaten;
    }
}

public class Main {

    static int n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static Queue<Shark> queue = new LinkedList<>();;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Shark shark = new Shark();

        // 맵 입력 및 아기 상어 위치 찾기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;  // 아기 상어 위치를 빈 칸으로 변경
                    shark = new Shark(i, j, 2, 0, 0);  // 초기 크기 2 고정
                }
            }
        }

        int totalTime = 0;
        while (true) {

            // 먹을 수 있는 물고기들 탐색 (BFS)
            ArrayList<Shark> fishes = fishEatingCount(new ArrayList<>(), shark);

            // 먹을 수 있는 물고기가 없으면 종료
            if (fishes.isEmpty()) {
                totalTime = shark.distance;
                break;
            }
            // 물고기가 1마리인 경우
            else if (fishes.size() == 1) {
                shark = fishes.get(0);
                shark = eatFish(shark);
                map[shark.x][shark.y] = 0;
            }
            // 물고기가 여러 마리인 경우 - 거리, 위쪽, 왼쪽 우선순위로 선택
            else {
                shark = selectClosestFish(fishes);
                shark = eatFish(shark);
                map[shark.x][shark.y] = 0;
            }
        }

        System.out.println(totalTime);
    }

    // 물고기 먹고 크기 증가 로직
    static Shark eatFish(Shark shark) {
        // 자기 크기만큼 먹으면 크기 증가
        if (shark.size == shark.eaten + 1) {
            return new Shark(shark.x, shark.y, shark.size + 1, shark.distance, 0);
        } else {
            return new Shark(shark.x, shark.y, shark.size, shark.distance, shark.eaten + 1);
        }
    }

    // 가장 가까운 물고기 선택 (거리 -> 위쪽 -> 왼쪽 우선순위)
    static Shark selectClosestFish(ArrayList<Shark> fishes) {
        // 1. 가장 가까운 거리 찾기
        int minDistance = Integer.MAX_VALUE;
        for (Shark fish : fishes) {
            minDistance = Math.min(minDistance, fish.distance);  // 제일 가까운 거리
        }

        // 2. 가장 가까운 거리의 물고기들만 필터링
        ArrayList<Shark> closest = new ArrayList<>();
        for (Shark fish : fishes) {
            if (minDistance == fish.distance) {
                closest.add(fish);
            }
        }

        // 3. 여러 마리면 위쪽, 왼쪽 우선순위로 선택
        if (closest.size() >= 2) {
            // 가장 위쪽 행 찾기
            int topRow = Integer.MAX_VALUE;
            for (Shark fish : closest) {
                topRow = Math.min(topRow, fish.x);
            }

            // 가장 위쪽 행에서 가장 왼쪽 열 찾기
            int leftCol = Integer.MAX_VALUE;
            for (Shark fish : closest) {
                if (topRow == fish.x) {
                    leftCol = Math.min(leftCol, fish.y);
                }
            }

            // 최종 선택된 물고기 반환
            for (Shark fish : closest) {
                if (topRow == fish.x && leftCol == fish.y) {
                    return fish;
                }
            }
        }

        // 가장 가까운 물고기가 한 마리라면
        return closest.get(0);
    }

    // BFS로 먹을 수 있는 물고기들 찾기
    static ArrayList<Shark> fishEatingCount(ArrayList<Shark> fishes, Shark start) {
        queue.offer(start);
        visited = new boolean[n][n];
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Shark current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 및 방문 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 이동 가능한 칸 (빈 칸이거나 내 크기보다 작거나 같은 물고기)
                if (map[nx][ny] == 0 || map[nx][ny] <= current.size) {
                    visited[nx][ny] = true;
                    queue.offer(new Shark(nx, ny, current.size, current.distance + 1, current.eaten));

                    // 먹을 수 있는 물고기 (내 크기보다 작은 물고기)
                    if (map[nx][ny] != 0 && map[nx][ny] < current.size) {
                        fishes.add(new Shark (nx, ny, current.size, current.distance + 1, current.eaten));
                    }
                }
            }
        }
        return fishes;
    }
}