import sys
sys.stdin = open('input.txt', 'r')

# Linked list (Node) 구현
class Node :
    def __init__(self, value = 0, next = None):
        self.value = value
        self.next = next

first = Node(1)
second = Node(2)
third = Node(3)
first.next = second
second.next = third
first.value = 6

# --------------------------------------------------

# Linked list를 활용하여 append 구현
class LinkedList(object):
    def __init__(self):
        self.head = None
        self.tail = None

    def append(self, value):  # O(n)
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
            self.tail = new_node
        # 맨 뒤의 node가 new_node를 가리켜야 한다.
        else:
            # current = self.head
            # while(current.next):
            #     current = current.next
            # current.next = new_node
            # head / tail version  >  O(1)
            self.tail.next = new_node
            self.tail = self.tail.next

    # 시간복잡도 - O(n)
    def get(self, idx):  # O(n)
        current = self.head
        for _ in range(idx):
            current = current.next
        return current.value
    
    # 시간복잡도 - O(n)
    def insert(self, idx, value):
        new_node = Node(value)
        if idx == 0:
            new_node.next = self.head
            self.head = new_node
        else:
            current = self.head
            for _ in range(idx - 1):
                current = current.next
            new_node.next = current.next
            current.next = new_node
        self.size += 1
    # 시간복잡도 - O(n)
    def remove(self, idx):
        if idx == 0:
            self.head = self.head.next
        else:
            current = self.head
            for _ in range(idx - 1):
                current = current.next
            current.next = current.next.next
        self.size -= 1

ll = LinkedList()
ll.append(1)
ll.append(2)
ll.append(3)
ll.append(4)
ll.get(0)
ll.get(1)
ll.get(3)
ll.insert(idx=2, value=9)
ll.remove(3)