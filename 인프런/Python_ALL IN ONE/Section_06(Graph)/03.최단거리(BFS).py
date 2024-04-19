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

grid1 = [ [1,0,0],
          [1,1,0],
          [1,0,0] ]
# output : -1

grid2 = [ [0,0,0],
          [1,1,0],
          [1,1,0] ]
# output : 4

grid3 = [ [0,0,0,1,0,0,0],
          [0,1,1,1,0,1,0],
          [0,1,0,0,0,1,0],
          [0,0,0,1,1,1,0],
          [0,1,0,0,0,0,0] ]
# output : 9



# from collections import deque

# def shortestPathBinaryMatrix(grid):
#     shortest_path_len = -1
#     row = len(grid)     # 행 길이
#     col = len(grid[0])  # 열 길이

#     if grid[0][0] != 0 or grid[row-1][col-1] != 0:
#         return shortest_path_len
    
#     dx = [-1, 1, 0, 0, -1, 1, -1, 1]  # 8방향 탐색
#     dy = [0, 0, -1, 1, 1, 1, -1, -1]  # 8방향 탐색
    
#     visited = [[False]*col for _ in range(row)]  # 방문했는지 확인
#     visited[0][0] = True  # 시작점은 방문완료
#     queue = deque()
#     queue.append((0, 0, 1))  # x좌표, y좌표, 현재길이(처음은 방문했으므로 1부터 시작)
#     while queue:
#         cur_x, cur_y, cur_len = queue.popleft()
        
#         if (cur_x == row-1) and (cur_y == col-1):  # 목적지에 도착하면 return
#             shortest_path_len = cur_len
#             break

#         for i in range(8):
#             next_x = cur_x + dx[i]
#             next_y = cur_y + dy[i]
#             # 범위 벗어나는지? 
#             if 0 <= next_x < row and 0 <= next_y < col:
#                 # 0인지 1인지? 방문을 했는지?
#                 if grid[next_x][next_y] == 0 and not visited[next_x][next_y]:
#                     queue.append((next_x, next_y, cur_len+1))
#                     visited[next_x][next_y] = True
    
#     return shortest_path_len

# print(shortestPathBinaryMatrix(grid2))