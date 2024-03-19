'''
    Tree
        - 서로 연결된 Node의 계층형 자료구조로써, root와 부모-자식 관계의 subtree로 구성되어 있다.     
    
    Tree 관련 개념
        - 노드(Node)     : 트리는 보통 노드로 구현된다.
        - 간선(Edge)     : 노드간에 연결된 선
        - 루트노드(Root) : 트리는 항상 루트에서 시작한다.
        - 리프노드(Leef) : 더이상 뻗어나갈 수 없는 마지막 노드
        - 자식노드(Child), 부모노드(Parent), 형제노드(Sibling)

        - 차수(degree)      : 각 노드가 갖는 자식의 수. 모든 노드의 차수가 n개 이하인 트리를 n진 트리라고 한다.
        - 조상(ancestor)    : 위쪽으로 간선을 따라가면 만나는 노드
        - 자손(descendant)  : 아래쪽으로 간선을 따라가면 만나는 노드
        - 높이(height)      : 루트노드에서 가장 멀리 있는 리프노드까지의 거리. 즉, 리프노드중에 최대 레벨값
        - 서브트리(subtree) : 트리의 어떤 노드를 루트로 하고, 그 자손으로 구성된 트리를 subtree라고 한다.
    
    이진트리 (Binary Tree)
        - 완전이진트리 : 모든 노드들이 전부있는 트리 (왼쪽에서 오른쪽으로 순회)
'''

class Node:
    def __init__(self, value = 0, left = None, right = None):
        self.value = value
        self.left = left
        self.right = right

class BinaryTree:
    def __init__(self):
        self.root = None

bt = BinaryTree()
bt.root = Node(value=1)
bt.root.left = Node(value=2)
bt.root.right =  Node(value=3)
bt.root.left.left = Node(value=4)
bt.root.left.right = Node(value=5)
bt.root.right.right = Node(value=6)
