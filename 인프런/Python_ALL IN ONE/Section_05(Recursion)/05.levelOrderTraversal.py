'''
    Tree 활용
        1. Tree 구현
        2. Tree 순회
            a. level order (대부분 BFS이며, FIFO자료구조 Queue 활용)
            b. postorder
            ※ a와 b가 코딩테스트에 빈번하게 출제된다.

    문제 푸는 순서
        1. 문제 이해하기
            - input, output 특징 확인
            - input size N 확인 (시간복잡도 계산)
            - 제약조건 확인 (시간복잡도 제한 체크 및 알고리즘 선택)
            - 예상할 수 있는 오류 파악

        2. 접근 방법
            - 직관적으로 생각하기
                - 보통 완전탐색으로 시작
                - 문제 상황을 단순화/극한화하여 생각하기
            
            - 자료구조와 알고리즘 활용
            
            - 메모리 사용
                - 시간복잡도를 줄이기 위해 사용하는 방법
                - 대표적으로 해시테이블 (딕셔너리)

        3. 코드 설계
        4. 코드 구현
'''

'''
    제목 : Maximum Depth of Binary Tree (이진트리 최대 깊이)
    설명 : Binary Tree 하나가 주어진다. 주어진 Binary Tree의 최대 깊이를 반환하라.
'''

# BFS 풀이 먼저 시작

from collections import deque

def maxDepth(root):
    max_depth = 0
    if root is None:
        return max_depth
    
    q = deque()
    q.append((root, 1))  # 처음 시작할 때 깊이는 1

    while q:
        cur_node, cur_depth = q.popleft()  # 현재 노드와 깊이반환
        max_depth = max(max_depth, cur_depth)
        if cur_node.left:
            q.append((cur_node.left, cur_depth + 1))
        if cur_node.right:
            q.append((cur_node.right, cur_depth + 1))
    return max_depth

root = [3, 9, 20, None, None, 15, 7]

maxDepth(root)