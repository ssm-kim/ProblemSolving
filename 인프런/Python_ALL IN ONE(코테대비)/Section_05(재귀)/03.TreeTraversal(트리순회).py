'''
    Tree Traversal
        - 트리순회란 트리탐색(search)라고도 불리며 '트리의 각 노드를 방문하는 과정'을 말한다.
          모든 노드를 한번씩 방문해야 하므로 '완전탐색'이라고도 불린다.
          순회 방법으로는 '너비우선탐색의 BFS'와 '깊이우선탐색의 DFS'가 있다.
'''

# bfs 기본 알고리즘 구현  ->  무조건 외워야 하며 queue를 사용한다.
from collections import deque

def bfs(root):
    visited = []
    if root is None:
        return 0  # []
    q = deque()
    q.append(root)

    while q:
        cur_node = q.popleft()
        visited.append(cur_node.value)

        if cur_node.left:
            q.append(cur_node.left)
        if cur_node.right:
            q.append(cur_node.right)
    return visited

bfs(root)