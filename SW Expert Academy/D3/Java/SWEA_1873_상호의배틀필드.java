import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    // 포탄 함수(물)
    public static boolean crossWater(int x, int y, int h, int w, char[][] grid) {
        if (0 <= x && x < h && 0 <= y && y < w) {
            if (grid[x][y] == '#') {  // 중간에 강철이 있다면 중지
                return true;
            }
            if (grid[x][y] == '*') {  // 물을 건너 벽돌이면 파괴
                grid[x][y] = '.';
                return true;
            }
        }
        return false;
    }

    // 전차 이동 함수
    public static boolean move(int i, int j, char pos, int[] previousPosition, char[][] grid, int h, int w) {
        int x = previousPosition[0];
        int y = previousPosition[1];

        if (0 <= i && i < h && 0 <= j && j < w) {
            if (grid[i][j] == '.') {
                grid[i][j] = pos;  // 이동 후 상하좌우 중 하나로 변경
                grid[x][y] = '.';  // 이동 전 좌표는 .로 변경
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();

        for (int t = 1; t <= tc; t++) {
            int h = scanner.nextInt();
            int w = scanner.nextInt();
            char[][] grid = new char[h][w];
            for (int i = 0; i < h; i++) {
                String line = scanner.next();
                grid[i] = line.toCharArray();
            }

            int totalCount = scanner.nextInt();
            char[] commands = scanner.next().toCharArray();
            int[] tankIdx = new int[2];
            char tankPos = ' ';

            // 초기 탱크 위치 찾기
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == '^' || grid[i][j] == '>' || grid[i][j] == 'v' || grid[i][j] == '<') {
                        tankIdx[0] = i;
                        tankIdx[1] = j;
                        tankPos = grid[i][j];
                        break;
                    }
                }
            }

            int x = tankIdx[0];
            int y = tankIdx[1];

            for (int i = 0; i < totalCount; i++) {
                char command = commands[i];

                switch (command) {
                    case 'U':
                        tankPos = '^';
                        int[] previousPositionU = {x, y};
                        x -= 1;
                        if (!move(x, y, tankPos, previousPositionU, grid, h, w)) {
                            x += 1;
                            grid[x][y] = tankPos;
                        }
                        break;

                    case 'R':
                        tankPos = '>';
                        int[] previousPositionR = {x, y};
                        y += 1;
                        if (!move(x, y, tankPos, previousPositionR, grid, h, w)) {
                            y -= 1;
                            grid[x][y] = tankPos;
                        }
                        break;

                    case 'D':
                        tankPos = 'v';
                        int[] previousPositionD = {x, y};
                        x += 1;
                        if (!move(x, y, tankPos, previousPositionD, grid, h, w)) {
                            x -= 1;
                            grid[x][y] = tankPos;
                        }
                        break;

                    case 'L':
                        tankPos = '<';
                        int[] previousPositionL = {x, y};
                        y -= 1;
                        if (!move(x, y, tankPos, previousPositionL, grid, h, w)) {
                            y += 1;
                            grid[x][y] = tankPos;
                        }
                        break;

                    case 'S':  // Shoot
                        char shotDir = grid[x][y];
                        int[] comeback = {x, y};

                        while (true) {
                            if (shotDir == '^') {
                                x -= 1;
                            } else if (shotDir == '>') {
                                y += 1;
                            } else if (shotDir == 'v') {
                                x += 1;
                            } else if (shotDir == '<') {
                                y -= 1;
                            }

                            if (0 <= x && x < h && 0 <= y && y < w) {
                                char next = grid[x][y];
                                if (next == '#') {  // 강철이면 중지
                                    break;
                                } else if (next == '-') {  // 물이면 다음 좌표까지 계속 진행
                                    if (shotDir == '^') {
                                        x -= 1;
                                        if (crossWater(x, y, h, w, grid)) {
                                            break;
                                        }
                                    } else if (shotDir == '>') {
                                        y += 1;
                                        if (crossWater(x, y, h, w, grid)) {
                                            break;
                                        }
                                    } else if (shotDir == 'v') {
                                        x += 1;
                                        if (crossWater(x, y, h, w, grid)) {
                                            break;
                                        }
                                    } else if (shotDir == '<') {
                                        y -= 1;
                                        if (crossWater(x, y, h, w, grid)) {
                                            break;
                                        }
                                    }
                                } else if (next == '*') {  // 벽돌이면 평지로 만든 후 중지
                                    grid[x][y] = '.';
                                    break;
                                } else if (next == '.') {  // 평지면 다음 좌표까지 계속 이동
                                    continue;
                                }
                            } else {  // 범위 벗어나면 반복문 중지
                                break;
                            }
                        }
                        x = comeback[0];
                        y = comeback[1];
                        break;
                }
            }

            System.out.print("#" + t + " ");
            for (int i = 0; i < h; i++) {
                System.out.println(new String(grid[i]));
            }
        }
    }
}
