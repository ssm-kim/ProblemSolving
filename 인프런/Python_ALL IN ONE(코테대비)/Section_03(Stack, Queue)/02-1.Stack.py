import sys
sys.stdin = open('(Python) ALL In ONE/input.txt', 'r', encoding='utf-8')

'''
    Stack
        - 시간 순서상 가장 최근에 추가한 데이터가 가장 먼저 나오는 후입선출(LIFO)
          형식으로 데이터를 저장하는 자료구조
        - stack의 top에 데이터를 추가하는 것을 push, 데이터를 추출하는 것을 pop
    
    직관적으로 생각하기
      - 보통 완전탐색으로 시작 (n**2)
      - 문제 상황을 단순화/극한화 해보기

    자료구조와 알고리즘 활용 (정렬 - nlogn)
      - 문제이해에서 파악한 내용을 토대로 어떤 자료구조를 사용화는게 가장 적합한지 결정
      - 대놓고 특정 자료구조와 알고리즘을 묻는 문제도 많음
      - 자료구조에 따라 선택할 수 있는 알고르짐을 문제에 적용

    메모리 사용
      - 시간복잡도를 줄이기 위해 메모리를 사용하는 방법
      - 대표적으로 해시테이블 !!
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