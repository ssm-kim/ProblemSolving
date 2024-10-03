import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    static char[][] map;
    static char[] command;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {
            int R = sc.nextInt();  // 행
            int C = sc.nextInt();  // 열
            int cx = 0, cy = 0;
            map = new char[R][C];
            for (int i = 0; i < R; i++) {
                String str = sc.next();
                map[i] = str.toCharArray();
                for (int j = 0; j < C; j++) {
                    char ch = str.charAt(j);
                    if (ch == '<' || ch == 'v' || ch == '^' || ch == '>') {
                        cx = i;
                        cy = j;
                        break;
                    }
                }  // 내 전차 위치 파악
            }

            // System.out.println(cx + " " + cy);

            int cmdCnt = sc.nextInt();
            command = sc.next().toCharArray();
            for (char cmd : command) {

                switch (cmd) {
                    case 'U':  // 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
                        map[cx][cy] = '^';
                        if (cx - 1 >= 0 && map[cx - 1][cy] == '.') {
                            map[cx - 1][cy] = map[cx][cy];  // 전차 이동
                            map[cx][cy] = '.';  // 평지 복귀
                            cx--;
                        }
                        break;
                    case 'D':
                        map[cx][cy] = 'v';
                        if (cx + 1 < R && map[cx + 1][cy] == '.') {
                            map[cx + 1][cy] = map[cx][cy];  // 전차 이동
                            map[cx][cy] = '.';  // 평지 복귀
                            cx++;
                        }
                        break;
                    case 'L':
                        map[cx][cy] = '<';
                        if (cy - 1 >= 0 && map[cx][cy - 1] == '.') {
                            map[cx][cy - 1] = map[cx][cy];  // 전차 이동
                            map[cx][cy] = '.';  // 평지 복귀
                            cy--;
                        }
                        break;
                    case 'R':
                        map[cx][cy] = '>';
                        if (cy + 1 < C && map[cx][cy + 1] == '.') {
                            map[cx][cy + 1] = map[cx][cy];  // 전차 이동
                            map[cx][cy] = '.';  // 평지 복귀
                            cy++;
                        }
                        break;
                    case 'S':
                        char myDir = map[cx][cy];
                        int nx = cx;
                        int ny = cy;

                        if (myDir == '>') {
                            while (true) {
                                ny++;
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;

                                if (map[cx][ny] == '#') break;

                                if (map[cx][ny] == '*') {
                                    map[cx][ny] = '.';
                                    break;
                                }  // 벽돌이면
                            }
                        } else if (myDir == '<') {
                            while (true) {
                                ny--;
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;

                                if (map[cx][ny] == '#') break;

                                if (map[cx][ny] == '*') {
                                    map[cx][ny] = '.';
                                    break;
                                }  // 벽돌이면
                            }

                        } else if (myDir == '^') {
                            while (true) {
                                nx--;
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;

                                if (map[nx][cy] == '#') break;

                                if (map[nx][cy] == '*') {
                                    map[nx][cy] = '.';
                                    break;
                                }  // 벽돌이면
                            }

                        } else if (myDir == 'v') {
                            while (true) {
                                nx++;
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;

                                if (map[nx][cy] == '#') break;

                                if (map[nx][cy] == '*') {
                                    map[nx][cy] = '.';
                                    break;
                                }  // 벽돌이면
                            }
                        }
                        break;
                }
            }

            sb.append("#").append(t).append(" ");
            for (char[] chars : map) {
                for (char aChar : chars) {
                    sb.append(aChar);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
