import sys
sys.stdin = open('(Python) ALL In ONE/input.txt', 'r', encoding='utf-8')

'''
    Stack
        - 시간 순서상 가장 최근에 추가한 데이터가 가장 먼저 나오는 후입선출(LIFO)
          형식으로 데이터를 저장하는 자료구조
        - stack의 top에 데이터를 추가하는 것을 push, 데이터를 추출하는 것을 pop
'''

# stack 선언
stack = []

# push O(1)
stack.append(1)
stack.append(2)
stack.append(3)
stack.append(4)

# push O(1)
stack.pop()
stack.pop()
stack.pop()