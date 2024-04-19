'''
Number of lslands
  - s(땅 1)과 s(물 0) 지도를 나타내는 (m x n) 2D 이진 그리드가 주어지면 섬의 총 갯수를 반환합니다. 
  - 섬은 물로 둘러싸여 있으며 인접한 육지가 수평 또는 수직으로 연결되어 형성됩니다.
    그리드의 네 모서리가 모두 물로 둘러싸여 있다고 가정할 수 있습니다.
'''
# TestCase01  >  output 3
grid2 = [
            ['1','1','1','1','0'],
            ['1','1','0','1','0'],
            ['1','1','0','0','0'],
            ['0','0','0','0','0']
]

# TestCase02  >  output 1
grid1 = [
            ['1','1','0','0','0'],
            ['1','1','0','0','0'],
            ['0','0','1','0','0'],
            ['0','0','0','1','1']
]

from collections import deque

def numIslands(grid):
    numsOfIslands = 0
    row = len(grid)
    col = len(grid[0])
    visited = [[False]*col for _ in range(row)]
    
    def bfs(x, y):
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]
        visited[x][y] = True  # 방문완료
        queue = deque()
        queue.append((x, y))  # BFS 시작좌표
        while queue:
            cur_x, cur_y = queue.popleft()
            for i in range(4):  # 상하좌우 방향탐색
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]
                # 범위를 벗어나는지?  땅인지? 이미 방문을 했는지?  총 3가지 예외처리필요
                if 0 <= next_x < row and 0 <= next_y < col:
                    if grid[next_x][next_y] == '1' and not visited[next_x][next_y]:
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))

    for i in range(row):
        for j in range(col):
            if grid[i][j] == '1' and not visited[i][j]:
                bfs(i, j)
                numsOfIslands += 1
    return numsOfIslands

print(numIslands(grid1))

# from collections import deque

# def numIslands(grid):
#     number_of_islands = 0
#     row = len(grid)     # 행
#     col = len(grid[0])  # 열
#     visited = [[False]*col for _ in range(row)]

#     def bfs(x, y):
#         dx = [-1, 1, 0, 0]  # 상하좌우탐색
#         dy = [0, 0, -1, 1]
#         visited[x][y] = True   # 방문완료
#         queue = deque()  # BFS 시작좌표
#         queue.append((x, y))
#         while queue:
#             cur_x, cur_y = queue.popleft()  # 현재인덱스
#             for i in range(4):
#                 next_x = cur_x + dx[i]      # 이동 후 인덱스
#                 next_y = cur_y + dy[i]
#                 # 범위를 벗어나는 경우, 이미방문한경우, 0(물)인경우
#                 if (next_x >= 0 and next_x < row) and (next_y >= 0 and next_y < col):  # 범위를 벗어나는 경우
#                     if grid[next_x][next_y] == '1' and not visited[next_x][next_y]:  # 이미방문했거나 0(물)인 경우
#                         visited[next_x][next_y] = True
#                         queue.append((next_x, next_y))
                
#     for i in range(row):
#         for j in range(col):
#             if grid[i][j] == '1' and not visited[i][j]:
#                 bfs(i, j)
#                 number_of_islands += 1
#     return number_of_islands

# print(numIslands(grid1), numIslands(grid2))