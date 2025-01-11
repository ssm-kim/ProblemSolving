import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int R, C, time;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<Node> waters = new LinkedList<>();
    static Queue<Node> hedgehog = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '*') waters.offer(new Node(i, j));
                if (map[i][j] == 'S') hedgehog.offer(new Node(i, j));
            }
        }

        boolean search = bfs();

        System.out.println(search ? time : "KAKTUS");
    }

    static boolean bfs() {
        time = 0;

        while (!hedgehog.isEmpty()) {  // 고슴도치가 이동할 수 있는 위치가 있는 동안 반복
            time++;  // 시간 증가

            // 1. 현재 턴의 물 확장 처리
            int waterSize = waters.size();  // 현재 턴에 존재하는 물의 개수
            while (waterSize-- > 0) {       // 현재 턴의 물만큼만 처리 (새로 추가된 물은 다음 턴에)
                Node nd = waters.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];

                    if (nx < 0 || nx >= R || ny < 0 | ny >= C) continue;

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        waters.offer(new Node(nx, ny));  // 다음 턴을 위해 물 위치 저장
                    }
                }
            }

            // 2. 현재 턴의 고슴도치 이동 처리
            int heggehogSize = hedgehog.size();  // 현재 턴에 고슴도치가 있는 위치의 개수
            while (heggehogSize-- > 0) {         // 현재 턴의 위치만큼만 처리 (새로 이동한 위치는 다음 턴에)
                Node nd = hedgehog.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];

                    if (nx < 0 || nx >= R || ny < 0 | ny >= C) continue;

                    if (map[nx][ny] == 'D') return true;

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'S';
                        hedgehog.offer(new Node(nx, ny));  // 다음 턴을 위해 고슴도치 위치 저장
                    }
                }
            }
        }
        return false;  // 더 이상 이동할 수 없으면 실패
    }
}