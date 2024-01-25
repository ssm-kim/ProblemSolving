import sys
sys.stdin = open('./input.txt', 'r')

'''
    프로그래머스 Level.01
      - 정답률 높은 문제 순서로 정렬
      - 1페이지 생략 2페이지부터 풀이시작 ( 10문제 단위 )
'''

# 내적
a = list(map(int, input().split(',')))
b = list(map(int, input().split(',')))
answer = 0
for x, y in zip(a, b):
    answer += x * y
print(answer)

# ----------------------------------------------------------------------