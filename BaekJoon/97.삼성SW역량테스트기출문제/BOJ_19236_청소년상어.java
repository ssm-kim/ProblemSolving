import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Shark {

    int x, y, dir;

    public Shark(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Fish {
    int x, y, number, dir;

    public Fish(int x, int y, int number, int dir) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.dir = dir;
    }
}

public class Main {
    static final int BOARD_SIZE = 4;
    static final int MAX_FISH = 16;

    static Shark shark;
    static int maxScore = 0;
    static int[][] board;
    static HashMap<Integer, Fish> fishMap = new LinkedHashMap<>();

    // 방향 :  ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 초기화 및 물고기 정보 저장
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < BOARD_SIZE; j++) {
                int fishNumber = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;  // 0부터 시작하도록 조정
                board[i][j] = fishNumber;
                fishMap.put(fishNumber, new Fish(i, j, fishNumber, dir));
            }
        }

        // 상어 초기 설정 : (0, 0) 물고기를 먹고 시작
        int sFish = board[0][0];
        Fish sFishInfo = fishMap.get(sFish);
        shark = new Shark(0, 0, sFishInfo.dir);
        board[0][0] = 0;  // 물고기를 먹은 자리는 빈 공간
        fishMap.remove(sFish);

        // 백트래킹 (첫 번째 물고긱 점수 포함)
        dfs(sFish);

        System.out.println(maxScore);
    }

    // 백트래킹을 통해 상어가 먹을 수 있는 물고기의 최대 점수를 계산
    static void dfs(int score) {
        // 1. 물고기들을 1번부터 16번까지 순서대로 이동
        moveFishes();

        boolean canEat = false;

        // 2. 상어가 현재 방향으로 이동할 수 있는 모든 경우 탐색
        for (int step = 1; step < BOARD_SIZE; step++) {
            int nx = shark.x + dx[shark.dir] * step;
            int ny = shark.y + dy[shark.dir] * step;

            // 범위 벗어나면 중지
            if (nx < 0 || nx >= BOARD_SIZE || ny < 0 || ny >= BOARD_SIZE) break;

            // 빈 칸은 지나감
            if (board[nx][ny] == 0) continue;

            canEat = true;

            // 3. 현재 상태 백업 (깊은 복사)
            int[][] boardCopy = copyBoard();
            HashMap<Integer, Fish> mapCopy = copyMap();
            Shark sharkCopy = new Shark(shark.x, shark.y, shark.dir);

            // 4. 물고기 먹기
            int fishNum = board[nx][ny];
            Fish targetFish = fishMap.get(fishNum);
            shark.x = nx;
            shark.y = ny;
            shark.dir = targetFish.dir;  // 먹은 물고기의 방향 획득
            board[nx][ny] = 0;
            fishMap.remove(fishNum);

            // 5. 재귀 호출로 다음 상태 탐색
            dfs(score + fishNum);

            // 6. 상태 복원
            board = boardCopy;
            fishMap = mapCopy;
            shark = sharkCopy;
        }

        // 더 이상 먹을 수 있는 물고기가 없으면 최댓값 갱신
        if (!canEat) maxScore = Math.max(maxScore, score);
    }

    // 1번부터 16번까지 물고기를 순서대로 이동
    static void moveFishes() {
        for (int fishNum = 1; fishNum <= 16; fishNum++) {
            Fish current = fishMap.get(fishNum);
            if (current == null) continue;  // 현재 위치에 물고기가 없으면 스킵 (이미 먹힌 경우)

            int cx = current.x;
            int cy = current.y;
            int curDir = current.dir;

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[(curDir + i) % 8];
                int ny = cy + dy[(curDir + i) % 8];

                // 범위 체크 및 상어 위치 체크
                if (nx < 0 || nx >= BOARD_SIZE || ny < 0 || ny >= BOARD_SIZE) continue;
                if (nx == shark.x && ny == shark.y) continue;

                // 빈 칸으로 이동 (이동 가능한 위치 발견)
                if (board[nx][ny] == 0) {
                    board[cx][cy] = 0;
                    board[nx][ny] = fishNum;
                    fishMap.put(fishNum, new Fish(nx, ny, fishNum, (curDir + i) % 8));
                }
                // 다른 물고기와 위치 교환
                else {
                    Fish other = fishMap.get(board[nx][ny]);

                    int posTmp = board[cx][cy];
                    board[cx][cy] = board[nx][ny];
                    board[nx][ny] = posTmp;
                    
                    // 현재 물고기는 기존 방향에서 i번만큼 회전함.
                    fishMap.put(fishNum, new Fish(nx, ny, fishNum, (curDir + i) % 8));

                    // 다른 물고기는 밀려난 것뿐이라서 원래 방향 유지.
                    fishMap.put(other.number, new Fish(cx, cy, other.number, other.dir));
                }
                break;  // 이동 완료
            }
        }
    }

    // 물고기 정보 맵을 깊은 복사
    static HashMap<Integer, Fish> copyMap() {
        HashMap<Integer, Fish> tmp = new HashMap<>();
        for (Entry<Integer, Fish> entry : fishMap.entrySet()) {
            Fish t = entry.getValue();
            tmp.put(entry.getKey(), new Fish(t.x, t.y, t.number, t.dir));  // 깊은 복사
        }
        return tmp;
    }

    // 보드를 깊은 복사
    static int[][] copyBoard() {
        int[][] tmp = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            tmp[i] = board[i].clone();  // 깊은 복사
        }
        return tmp;
    }
}