'''
    Lowest Common Ancestor of a Binary Tree
        - 문제에서 Binary 트리 하나와 해당 트리에 속한 두 개의 노드가 주어진다.
          이때, 두 노드의 공통 조상중 가장 낮은 Node 즉, the lowest common ancestor(LCA)를 찾아라
    ※ 인프런 강의로 개념 다시 잡기

    항상 제일 먼저 직관적으로 생각하기
    left, right 노드 방문해서 조건문에 따른 값 리턴 받기

    직접 그려보고 무조건 외우기 !!
'''

class Solution(object):
    def LCA(self, root, p, q):
        if root == None:
            return None

        left = self.LCA(root.left, p, q)
        right = self.LCA(root.right, p, q)

        if root == p or root == q:
            return root
        elif left and right:
            return root
        elif left:
            return left
        elif right:
            return right

    print(LCA([3, 5, 1, 6, 2, 0, 8, None, None, 7, 4], 6, 4))