'''
    Keys and Rooms

     - 0번방부터 n-1번방까지 총 n개의 방이 있다. 0번 방을 제외한 모든 방은 잠겨있다.
     - 우리의 목표는 모든방에 visit하는 것이다. 하지만 잠겨져 있는 방은 key가 없으면 visit할 수 없다.
     - 각 방에 방문할 때, 별개의 열쇠뭉치를 찾을 수도 있다.
     - 각각의 열쇠에는 number가 쓰여져 있고 해당 번호에 해당하는 방을 잠금 해제할 수 있다.
     - 열쇠뭉치는 모두 가져갈 수 있고 언제든 방문을 열기 위해 사용할 수 있다.
    
    문제에서 rooms 배열이 주어지고 rooms[i]는 해당 방에서 얻을 수 있는 열쇠뭉치 목록을 표현한다.
    모든 방을 visit할 수 있다면 True, 그렇지 않다면 False를 반환하라.
    
    제약조건
     - n == rooms.length
     - 2 <= n <= 1000
     - 0 <= rooms[i].length <= 1000
     - 1 <= sum(rooms[i].length) <= 3000
     - 0 <= rooms[i][j] < n
'''

rooms1 = [[1, 3], [3, 0, 1], [2], [0]]
# output : False

rooms2 = [[1], [2], [3], []]
# output : True

rooms3 = [[1,3], [2,4], [0], [4], [], [3,4]]
# output : False
import sys

# DFS
def canVisitAllRooms(rooms):
    visited = [False] * len(rooms)  # 5번방 방문 안했네?  ->  False // True
    
    # v 에 연결되어있는 모든 정점에 방문할거다. 
    def dfs(v):
        visited[v] = True
        for next_v in rooms[v]:
            if visited[next_v] == False:
                dfs(next_v)
    dfs(0)

    if False in visited:
        return False
    return True

print(canVisitAllRooms(rooms3))

# BFS 
from collections import deque

def canVisitAllRooms(rooms):
    visited = [False] * len(rooms)
    
    # v 에 연결되어있는 모든 정점에 방문할거다. 
    def bfs(v):
        queue = deque()
        queue.append(v)
        visited[v] = True

        while queue:
            cur_v = queue.popleft()
            for next_v in rooms[cur_v]:
                if visited[next_v] == False:
                    queue.append(next_v)
                    visited[next_v] = True
    bfs(0)

    return all(visited)

print(canVisitAllRooms(rooms3))