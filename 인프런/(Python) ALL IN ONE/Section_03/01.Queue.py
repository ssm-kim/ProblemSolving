import sys
sys.stdin = open('(Python) ALL In ONE/input.txt', 'r', encoding='utf-8')

'''
    Queue
        - 시간 순서상 먼저 저장한 데이터가 먼저 출력되는 선입선출(FIFO)형식으로 
          데이터를 저장하는 자료구조이다.
        - rear(뒤)에 데이터를 추가하는 것을 enqueue
        - front(앞)에서 데이터를 꺼내는 것을 dequeue
'''
# queue 선언
q = []

# enqueue O(1)
q.append(1)
q.append(2)
q.append(3)
q.append(4)

# enqueue O(n)
q.pop(0)
q.pop(0)
q.pop(0)
print(q, end='\n\n')
print('--------------------------------------------------', end='\n\n')

'''
    enqueue O(n)을 해결하기 위해서 구현해놓은 linked list로 구현된 deque() 활용
    deque 자료구조는 양방향으로 enqueue와 dequeue가 가능하므로 둘 다 O(1) !!
'''

from collections import deque
queue = deque()
# enqueue() O(1)
queue.append(1)
queue.append(2)
queue.append(3)
queue.append(4)

# dequeue() O(1)
queue.popleft()
queue.popleft()
queue.popleft()
print(queue)