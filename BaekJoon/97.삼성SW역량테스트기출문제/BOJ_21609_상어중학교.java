import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Block implements Comparable<Block> {
    int x, y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Block other) {
        if (this.x == other.x) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
    }
}

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int[][] map;
    static ArrayList<Block> bestGroup = new ArrayList<>();
    static ArrayList<Block> finalGroup;
    static int bestRainbowCnt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;

        // 게임 메인 루프
        while (true) {
            // 초기화
            bestGroup = new ArrayList<>();
            finalGroup = new ArrayList<>();
            visited = new boolean[n][n];  // 일반 블록용 방문 배열
            bestRainbowCnt = 0;

            // 1. 최대 크기 블록 그룹 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 일반 블록(1~m)에서만 시작
                    if (map[i][j] <= 0 || map[i][j] == 9 || visited[i][j]) continue;
                    maxSizeBlocks(i, j);
                }
            }

            // 더 이상 제거할 그룹이 없으면 게임 종료
            if (finalGroup.isEmpty()) break;

            // 2. 블록 제거 및 점수 획득
            score += finalGroup.size() * finalGroup.size();
            for (Block block : finalGroup) {
                map[block.x][block.y] = 9;  // 빈 칸으로 변경
            }

            // 3. 중력 → 4. 반시계 회전 → 5. 중력
            downBlocks();
            rotate();
            downBlocks();
        }

        System.out.println(score);
    }

    static void maxSizeBlocks(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        ArrayList<Block> curGroup = new ArrayList<>();
        curGroup.add(new Block(sx, sy));
        int curRainbowCnt = 0;

        // 핵심: 무지개 블록은 각 BFS마다 별도 관리
        boolean[][] rainbowVisited = new boolean[n][n];

        // BFS로 연결된 블록 그룹 탐색
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == -1 || map[nx][ny] == 9) continue;

                // 같은 색 일반 블록 또는 무지개 블록만 연결
                if (map[sx][sy] == map[nx][ny] || map[nx][ny] == 0) {

                    if (map[nx][ny] == 0) {
                        // 무지개 블록: 지역 배열로 방문 관리
                        if (rainbowVisited[nx][ny]) continue;
                        rainbowVisited[nx][ny] = true;
                        curRainbowCnt++;
                    } else {
                        // 일반 블록: 전역 배열로 방문 관리
                        if (visited[nx][ny]) continue;
                        visited[nx][ny] = true;
                    }

                    queue.offer(new int[]{nx, ny});
                    curGroup.add(new Block(nx, ny));
                }
            }
        }

        // 크기 2 이상인 그룹만 유효
        if (curGroup.size() >= 2) {
            if (bestGroup.isEmpty()) {
                bestGroup = curGroup;
                bestRainbowCnt = curRainbowCnt;
                finalGroup = curGroup;
            } else {
                // 우선순위: 크기 → 무지개 개수 → 기준 블록 위치
                if (curGroup.size() == bestGroup.size()) {
                    if (curRainbowCnt == bestRainbowCnt) {
                        // 기준 블록 비교 (무지개 아닌 블록 중 행,열 최소)
                        Collections.sort(curGroup);
                        Collections.sort(bestGroup);

                        Block curStandard = null;
                        for (Block b : curGroup) {
                            if (map[b.x][b.y] != 0) {
                                curStandard = b;
                                break;
                            }
                        }

                        Block bestStandard = null;
                        for (Block b : bestGroup) {
                            if (map[b.x][b.y] != 0) {
                                bestStandard = b;
                                break;
                            }
                        }

                        // 기준 블록의 행이 큰 것, 행이 같으면 열이 큰 것 선택
                        if (curStandard.x == bestStandard.x) {
                            selectGroup(curStandard.y, bestStandard.y, curGroup, curRainbowCnt);
                        } else {
                            selectGroup(curStandard.x, bestStandard.x, curGroup, curRainbowCnt);
                        }
                    } else {
                        selectGroup(curRainbowCnt, bestRainbowCnt, curGroup, curRainbowCnt);
                    }
                } else {
                    selectGroup(curGroup.size(), bestGroup.size(), curGroup, curRainbowCnt);
                }
            }
        }
    }

    // 더 좋은 그룹이면 교체
    static void selectGroup(int curGroupCnt, int bestGroupCnt, ArrayList<Block> curGroup, int curRainbowCnt) {
        if (curGroupCnt > bestGroupCnt) {
            finalGroup = curGroup;
            bestGroup = curGroup;
            bestRainbowCnt = curRainbowCnt;
        } else {
            finalGroup = bestGroup;
        }
    }

    // 중력 적용: 빈 칸에서 위쪽 블록 찾아서 떨어뜨리기
    static void downBlocks() {
        for (int col = 0; col < n; col++) {
            for (int row = n - 1; row >= 0; row--) {
                if (map[row][col] == 9) {  // 빈 칸이면
                    int searchRow = row;

                    while (true) {
                        searchRow--;
                        if (searchRow < 0) break;
                        if (map[searchRow][col] == -1) break;  // 검은색 블록에서 멈춤

                        if (map[searchRow][col] != 9) {  // 블록 찾으면
                            map[row][col] = map[searchRow][col];
                            map[searchRow][col] = 9;
                            break;
                        }
                    }
                }
            }
        }
    }

    // 90도 반시계 회전: 테두리별로 각 변을 한 칸씩 이동
    static void rotate() {
        for (int layer = 0; layer < n / 2; layer++) {
            int start = layer;
            int end = n - 1 - layer;
            int size = end - start + 1;

            // 각 변의 값들을 저장
            int[] top = new int[size];
            int[] right = new int[size];
            int[] bottom = new int[size];
            int[] left = new int[size];

            for (int i = 0; i < size; i++) {
                top[i] = map[start][start + i];
                right[i] = map[start + i][end];
                bottom[i] = map[end][end - i];
                left[i] = map[end - i][start];
            }

            // 반시계 방향으로 각 변을 다음 위치로 이동
            for (int i = 0; i < size; i++) {
                map[start][start + i] = right[i];      // 오른쪽 → 위
                map[end - i][start] = top[i];          // 위 → 왼쪽
                map[end][end - i] = left[i];           // 왼쪽 → 아래
                map[start + i][end] = bottom[i];       // 아래 → 오른쪽
            }
        }
    }
}