'''
1091. Shortest Path in Binary Matrix(이진 행렬의 최단 경로)

  - n*n 이진 행렬인 grid가 주어졌을 때, 출발지에서 목적지까지 도착하는 가장 빠른 경로의 길이를 반환하시오. 만약 경로가 없으면 -1을 반환합니다.
  - 출발지 : top-left cell  /  목적지 : bottom-right cell
  - 값이 0인 cell만 지나갈 수 있다.
  - cell끼리는 8가지 방향으로 연결되어 있다. (edge와 corner 방향으로 총 8가지이다.)
  - 연결된 cell을 통해서만 지나갈 수 있다.
  제약조건
    - n == grid.length
    - n == grid[i].length
    - 1 <= n <= 100
    - grid[i][j] is 0 or 1

leetcode - https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
'''

# BFS활용  >  미로찾기문제 또는 최단거리문제에 주로 사용된다.

from collections import deque
grid = [ [1,0,0],
         [1,1,0],
         [1,0,0] ]
# output : -1

grid = [ [0,0,0],
         [1,1,0],
         [1,1,0] ]
# output : 4

def shortestPathBinaryMatrix(grid):
    shortest_path_len = -1
    row = len(grid)
    col = len(grid[0])

    if grid[0][0] != 0 or grid[row-1][col-1] != 0 :  # 가지치기
        return shortest_path_len
    
    visited = [[False]*col for _ in range(row)]

    delta = [(-1, 0), (1, 0), (0, -1), (0, 1), 
             (1, 1), (-1, 1), (1, -1), (-1, -1)]

    queue = deque()  # 시작점 좌상단좌표, 현재 길이
    queue.append((0, 0, 1))
    visited[0][0] = True   # 시작점은 방문완료

    while queue:
        cur_row, cur_col, cur_len = queue.popleft()
        # 목적지에 도착했을 때 cur_len를 shortest_path_len에 저장하면 된다.
        if (cur_row == row-1) and (cur_col == col-1):
            shortest_path_len = cur_len
            break
        # 연결되어있는 정점(vertex) 확인하기
        for dr, dc in delta:
            next_row = cur_row + dr
            next_col = cur_col + dc
            if (0 <= next_row and next_row < row) and (0 <= next_col and next_col < col):
                if grid[next_row][next_col] == 0 and not visited[next_row][next_col]:
                    queue.append((next_row, next_col, cur_len + 1))
                    visited[next_row][next_col] = True

    return shortest_path_len

print(shortestPathBinaryMatrix(grid))