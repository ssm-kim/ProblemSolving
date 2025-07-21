import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int[] col = new int[n];
            for (int j = 0; j < n; j++) {
                col[j] = map[j][i];
            }
            // 행
            if (check(map[i])) answer++;
            // 열
            if (check(col)) answer++;
        }
        System.out.println(answer);
    }

    static boolean check(int[] line) {
        boolean[] visited = new boolean[n];  // 경사로 중복 설치 방지
        for (int i = 1; i < n; i++) {
            int current = line[i - 1];
            int next = line[i];

            // 높은 곳 -> 낮은 곳: 낮은 구간에 경사로 설치
            if (current > next) {
                if (current - next == 1) {  // 높이 차이가 1인지 확인
                    if (i + l > n) return false;  // 경사로 놓을 공간 확인

                    // L개의 칸으로 (연속)되어 놓을 수 있는지? + 이미 경사로 놓여져 있는지 확인
                    int count = 0;
                    for (int j = i; j < i + l; j++) {
                        if (next == line[j] && !visited[j]) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == l) {
                        // 낮은 구간에 경사로 설치
                        for (int j = i; j < i + l; j++) {
                            visited[j] = true;
                        }
                    } else {  // 경사로 L개를 설치할 수 없다면
                        return false;
                    }
                } else {  // 높이 차이가 1이 아니면
                    return false;
                }
            }
            // 낮은 곳 -> 높은 곳: 이전(낮은) 구간에 경사로 설치
            else if (current < next) {
                if (next - current == 1) {  // 높이 차이가 1인지 확인
                    if (i - l < 0) return false;  // 경사로 놓을 공간 확인

                    // L개의 칸으로 (연속)되어 놓을 수 있는지? + 이미 경사로 놓여져 있는지 확인
                    int count = 0;
                    for (int j = i - 1; j >= i - l; j--) {
                        if (current == line[j] && !visited[j]) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == l) {
                        // 낮은 구간에 경사로 설치
                        for (int j = i - 1; j >= i - l; j--) {
                            visited[j] = true;
                        }
                    } else {  // 경사로 L개를 설치할 수 없다면
                        return false;
                    }
                } else {  // 높이 차이가 1이 아니면
                    return false;
                }
            }
        }
        return true;
    }
}