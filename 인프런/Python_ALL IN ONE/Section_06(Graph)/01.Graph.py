'''
    그래프
        - 그래프G(V, E)는 어떤 자료나 개념을 표현하는 정점들의 집합 V(vertex)와 이들을 연결하는
          간선(Edge)들의 집합 E로 구성된 자료구조이다.
    그래프 순회
        - 깊이우선탐색과 너비우선탐색 2가지 알고리즘이 존재한다.  
'''

# BFS, DFS 공통으로 사용되는 딕셔너리
graph = {
    'A': ['B', 'D', 'E'],
    'B': ['A', 'C', 'D'],
    'C': ['B'],
    'D': ['A', 'B'],
    'E': ['A']
}

# 너비 우선 탐색(Breadth-first search, BFS) 기본코드  ->  외워야 코테 적용 가능
# from collections import deque
# def bfs(graph, start_v):
#     visited = [start_v]
#     queue = deque(start_v)
#     while queue:
#         cur_v = queue.popleft()
#         for v in graph[cur_v]:
#             if v not in visited:
#                 visited.append(v)  # 방문하고
#                 queue.append(v)    # 큐에 넣는다.
#             print(visited)
# bfs(graph, 'A')

# 깊이 우선 탐색(Depth-first search, DFS) - 기본코드  ->  외워야 코테 적용 가능

visited = []
def dfs(cur_v):
    visited.append(cur_v)
    for v in graph[cur_v]:
        if v not in visited:
            dfs(v)
    return visited

print(dfs('A'))