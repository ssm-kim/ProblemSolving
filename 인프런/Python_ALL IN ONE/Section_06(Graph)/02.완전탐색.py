'''
Number of lslands
  - s(땅 1)과 s(물 0) 지도를 나타내는 (m x n) 2D 이진 그리드가 주어지면 섬의 총 갯수를 반환합니다. 
  - 섬은 물로 둘러싸여 있으며 인접한 육지가 수평 또는 수직으로 연결되어 형성됩니다.
    그리드의 네 모서리가 모두 물로 둘러싸여 있다고 가정할 수 있습니다.
'''
# TestCase01  >  output 1
grid1 = [
            ['1','1','1','1','0'],
            ['1','1','0','1','0'],
            ['1','1','0','0','0'],
            ['0','0','0','0','0']
]

# TestCase02  >  output 3
grid2 = [
            ['1','1','0','0','0'],
            ['1','1','0','0','0'],
            ['0','0','1','0','0'],
            ['0','0','0','1','1']
]

from collections import deque
def numIslands(grid):
    numOfIslands = 0  # 섬의 총 갯수
    row = len(grid)     # 행 길이
    col = len(grid[0])  # 열 길이
    visited = [[False]*col for _ in range(row)]

    def bfs(x, y):
        visited[x][y] = True
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]
        queue = deque()
        queue.append((x, y))
        while queue:
            cur_row, cur_col = queue.popleft()
            for i in range(4):
                next_row = cur_row + dx[i]
                next_col = cur_col + dy[i]
                # 범위를 벗어나는지?
                if 0 <= next_row < row and 0 <= next_col < col:
                    # 땅인지 물인지? 방문을 했는지?
                    if grid[next_row][next_col] == '1' and not visited[next_row][next_col]:
                        visited[next_row][next_col] = True
                        queue.append((next_row, next_col))

    for i in range(row):
        for j in range(col):
            if grid[i][j] == '1' and not visited[i][j]:
                bfs(i, j)
                numOfIslands += 1
    return numOfIslands

print(numIslands(grid2))


# from collections import deque

# def numIslands(grid):
#     numsOfIslands = 0
#     row = len(grid)
#     col = len(grid[0])
#     visited = [[False]*col for _ in range(row)]
    
#     def bfs(x, y):
#         dx = [-1, 1, 0, 0]
#         dy = [0, 0, -1, 1]
#         visited[x][y] = True  # 방문완료
#         queue = deque()
#         queue.append((x, y))  # BFS 시작좌표
#         while queue:
#             cur_x, cur_y = queue.popleft()
#             for i in range(4):  # 상하좌우 방향탐색
#                 next_x = cur_x + dx[i]
#                 next_y = cur_y + dy[i]
#                 # 범위를 벗어나는지?  땅인지? 이미 방문을 했는지?  총 3가지 예외처리필요
#                 if 0 <= next_x < row and 0 <= next_y < col:
#                     if grid[next_x][next_y] == '1' and not visited[next_x][next_y]:
#                         visited[next_x][next_y] = True
#                         queue.append((next_x, next_y))

#     for i in range(row):
#         for j in range(col):
#             if grid[i][j] == '1' and not visited[i][j]:
#                 bfs(i, j)
#                 numsOfIslands += 1
#     return numsOfIslands

# print(numIslands(grid1))