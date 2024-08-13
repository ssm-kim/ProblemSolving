import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int[][] board = new int[100][100];    // 사다리 영역
    static int[][] visited = new int[100][100];  // 방문 배열
    static int[] dx = { 0, 0, 1 };               // 좌 > 우 > 아래 방향 탐색
    static int[] dy = { -1, 1, 0 };
    static boolean arrive;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            sc.nextInt();  // TC 번호 읽기

            for (int i = 0; i < 100; i++) {  // 입력 정보 받기
                for (int j = 0; j < 100; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            int ans = 0;
            arrive = false;  // 도착 여부 초기화
            for (int col = 0; col < 100; col++) {
                if (board[0][col] == 1) {  // 출발점 발견
                    dfs(0, col);       // 깊이 우선 탐색
                    if (arrive) {    // 도착 지점에 왔다면
                        ans = col;   // 현재 col 값으로 변경
                        break;       // 탐색 종료
                    }
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }

    static void dfs(int cx, int cy) {

        // 도착 지점에 도달한 경우
        if (cx == 99 && board[cx][cy] == 2) {
            arrive = true;  // 도착 플래그 설정
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100) {
                continue;  // 범위를 벗어나면 다음 방향 확인
            }

            // 방문하지 않았고, 사다리가 있는 경우
            if (visited[nx][ny] == 0 && board[nx][ny] > 0) {
                visited[nx][ny] = 1;  // 방문 표시
                dfs(nx, ny);
                visited[nx][ny] = 0;  // 백트래킹 : 방문 표시 제거
                return;  // 현재 탐색 종료
            }
        }
    }
}