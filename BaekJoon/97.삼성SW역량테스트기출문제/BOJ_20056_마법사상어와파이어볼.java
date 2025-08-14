import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class fireBall {
    int r, c, m, s, d;

    public fireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {

    // 8방향: 북, 북동, 동, 남동, 남, 남서, 서, 북서
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static int n, m, k;
    static int[][] map;
    static ArrayList<fireBall> lstInfo;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 가로 세로 크기
        m = Integer.parseInt(st.nextToken());  // 처음 파이어볼 발사한 개수
        k = Integer.parseInt(st.nextToken());  // 이동 명령 횟수

        lstInfo = new ArrayList<>();  // 각 파이어볼의 상태 정보 관리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 행, 열 based-zero 인덱스 활용
            int r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());  // 질량
            int s = Integer.parseInt(st.nextToken());  // 속력
            int d = Integer.parseInt(st.nextToken());  // 방향

            lstInfo.add(new fireBall(r, c, m, s, d));
        }

        // K번 이동 명령 수행
        for (int i = 0; i < k; i++) {
            map = new int[n][n];  // 매 턴마다 맵 초기화

            // 1단계: 모든 파이어볼 이동
            ArrayList<fireBall> movedFireBall = moveFireBall();

            // 2단계: 2개 이상 겹친 곳에서 합치기/분할
            spreadFireBall(movedFireBall);
        }

        int answer = 0;
        for (fireBall fb : lstInfo) {
            answer += fb.m;
        }
        System.out.println(answer);
    }

    static void spreadFireBall(ArrayList<fireBall> movedFireBall) {
        lstInfo = new ArrayList<>();  // 먼저 완전히 초기화

        // 2개 이상 겹친 좌표들 추출 (중복 제거)
        HashSet<String> path = new HashSet<>();
        for (fireBall fb : movedFireBall) {
            if (map[fb.r][fb.c] == 1) {  // 1개만 있는 파이어볼은 그대로 유지
                lstInfo.add(fb);
            } else if (map[fb.r][fb.c] >= 2) {  // 2개 이상인 좌표를 저장
                path.add(fb.r + "," + fb.c);
            }
        }

        // 각 겹친 좌표에서 파이어볼 합치기/분할 처리
        for (String coordinate : path) {
            String[] parts = coordinate.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);

            // 해당 좌표의 모든 파이어볼 정보 수집
            int totalMass = 0; int totalSpeed = 0; int cnt = 0;
            ArrayList<Integer> directions = new ArrayList<>();  // 합쳐진 파이어볼의 방향

            for (fireBall fb : movedFireBall) {
                if (r == fb.r && c == fb.c) {
                    totalMass += fb.m;
                    totalSpeed += fb.s;
                    cnt++;
                    directions.add(fb.d);
                }
            }

            // 새로운 파이어볼 속성 계산
            int newMass = totalMass / 5;
            int newSpeed = totalSpeed / cnt;

            // 방향 홀짝성 체크
            int dirCnt = 0;
            for (int dir : directions) {
                if (dir % 2 == 0) dirCnt++;
            }

            // 질량이 0이면 소멸
            if (newMass > 0) {
                // 모두 홀수이거나 모두 짝수인 경우: 0,2,4,6 방향
                // 그 외의 경우: 1,3,5,7 방향

                int[] newDirections = (dirCnt == 0 || dirCnt == directions.size())
                    ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                for (int dir : newDirections) {
                    lstInfo.add(new fireBall(r, c, newMass, newSpeed, dir));
                }
            }
        }
    }

    static ArrayList<fireBall> moveFireBall() {
        ArrayList<fireBall> movedList = new ArrayList<>();

        for (fireBall fb : lstInfo) {
            // 파이어볼 이동 계산
            int nx = fb.r + (dx[fb.d] * fb.s);
            int ny = fb.c + (dy[fb.d] * fb.s);

            // 토러스 형태 경계 처리 (1번행과 N번행, 1번열과 N번열 연결)
            while (nx < 0) nx += n;
            while (ny < 0) ny += n;
            nx %= n;
            ny %= n;

            // 해당 위치의 파이어볼 개수 증가
            map[nx][ny]++;

            movedList.add(new fireBall(nx, ny, fb.m, fb.s, fb.d));
        }
        return movedList;
    }
}