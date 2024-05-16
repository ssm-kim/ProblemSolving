import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''
# 28278. 스택 2
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# commands = [list(map(int, input().split())) for _ in range(n)]
# stack, res = list(), list()
# for command in commands:
#     if len(command) == 2:
#         a, b = command
#         stack.append(b)
#     else:
#         x = command[0]
#         if x == 2:
#             if stack:
#                 res.append(stack.pop())
#             else:
#                 res.append(-1)
#         elif x == 3:
#             res.append(len(stack))
#         elif x == 4:
#             if stack:
#                 res.append(0)
#             else:
#                 res.append(1)
#         else:
#             if stack:
#                 res.append(stack[-1])
#             else:
#                 res.append(-1)
# for i in res:
#     print(i)

# --------------------------------------------------

# 10773. 제로
'''
    문제풀이방법 및 접근
'''
# k = int(input())
# stack = list()
# for i in range(k):
#     num = int(input())
#     if num == 0:
#         stack.pop()
#     else:
#         stack.append(num)
# print(sum(stack))

# --------------------------------------------------

# 9012. 괄호 
'''
    문제풀이방법 및 접근
'''
# tc = int(input())
# for _ in range(tc):
#     st = list(input())
#     stack = list()
#     answer = 'YES'
#     for i in st:
#         if i == '(':
#             stack.append(i)
#         elif i == ')':
#             if stack and (stack[-1] == '('):
#                 stack.pop()
#             else:
#                 stack.append(i)
#     if stack:
#         answer = 'NO'
#     print(answer)

# --------------------------------------------------

# 4949. 균형잡힌 세상 
'''
    문제풀이방법 및 접근
'''
# while True:
#     check_st = input()
#     if check_st == '.':
#         break
    
#     check_st = list(check_st)
#     stack, answer = list(), 'yes'
    
#     for i in range(len(check_st)):
#         st = check_st[i]
#         if st == '(' or st == '[':
#             stack.append(st)
#         else:
#             if st == ')':
#                 if stack and stack[-1] == '(':
#                     stack.pop()
#                 else:
#                     stack.append(st)
#             elif st == ']':
#                 if stack and stack[-1] == '[':
#                     stack.pop()
#                 else:
#                     stack.append(st)

#     if stack:
#         answer = 'no'
#     print(answer)

# --------------------------------------------------

# 12789.도키도키 간식드리미  >  다시 풀어보기
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# standing = list(map(int, input().split()))
# stack = list()
# target = 1
#
# while standing:
#     curr = standing.pop(0)
#     if curr == target:
#         target += 1
#     else:
#         stack.append(curr)
#
#     for i in range(len(stack)-1, -1, -1):
#         if stack[i] == target:
#             stack.pop()
#             target += 1
#         else:
#             break
#
# print('Nice' if not stack else 'Sad')

# --------------------------------------------------

# 18258. 큐 2
'''
    문제풀이방법 및 접근
'''
# import sys
# from collections import deque
# n = int(input())
# queue = deque()
# answer = list()
# for i in range(n):
#     command = list(sys.stdin.readline().split())
#     if len(command) == 2:
#         a, b = command[0], int(command[1])
#         queue.append(b)
#     else:
#         command = command.pop()
#         if command == 'pop':
#             if queue:
#                 answer.append(queue.popleft())
#             else:
#                 answer.append(-1)
#         elif command == 'size':
#             answer.append(len(queue))
#         elif command == 'empty':
#             if queue:
#                 answer.append(0)
#             else:
#                 answer.append(1)
#         elif command == 'front':
#             if queue:
#                 answer.append(queue[0])
#             else:
#                 answer.append(-1)
#         elif command == 'back':
#             if queue:
#                 answer.append(queue[-1])
#             else:
#                 answer.append(-1)
# for i in answer:
#     print(i)

# --------------------------------------------------

# 2164. 카드2
'''
    문제풀이방법 및 접근
'''
# from collections import deque
# n = int(input())
# queue = deque(i for i in range(1, n+1))
#
# while True:
#     if len(queue) == 1:
#         print(queue[0])
#         break
#     queue.popleft()  # 카드를 버린다.
#     mv = queue.popleft()  # 첫번째 카드를 마지막 순서로 옮긴다.
#     queue.append(mv)

# --------------------------------------------------

# 11866. 요세푸스 문제 0
'''
    문제풀이방법 및 접근
        - 이제 순서대로 K번째 사람을 제거한다.
        - 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
        - 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
'''
# from collections import deque
#
# n, k = map(int, input().split())
# queue = deque(i for i in range(1, n+1))
# answer = list()  # 요세푸스 순열
# idx = 0          # 인덱스 번호
# while queue:
#     print(queue)
#     idx += 1
#     if idx == k:
#         idx = idx % k
#         answer.append(queue.popleft())
#         continue
#     queue.append(queue.popleft())
#
# print('<{}>'.format(', '.join(map(str, answer))))

# --------------------------------------------------

# 28279. 덱 2
'''
    문제풀이방법 및 접근
'''
# import sys
# from collections import deque
# n = int(input())
# dq = deque()
# answer = list()
# for i in range(n):
#     command = list(map(int, sys.stdin.readline().split()))
#     if len(command) == 2:
#         a, b = command
#         if a == 1:
#             dq.appendleft(b)
#         elif a == 2:
#             dq.append(b)
#     else:
#         command = command.pop()
#         if command == 3:
#             if dq:
#                 answer.append(dq.popleft())
#             else:
#                 answer.append(-1)
#         elif command == 4:
#             if dq:
#                 answer.append(dq.pop())
#             else:
#                 answer.append(-1)
#         elif command == 5:
#             answer.append(len(dq))
#         elif command == 6:
#             if dq:
#                 answer.append(0)
#             else:
#                 answer.append(1)
#         elif command == 7:
#             if dq:
#                 answer.append(dq[0])
#             else:
#                 answer.append(-1)
#         elif command == 8:
#             if dq:
#                 answer.append(dq[-1])
#             else:
#                 answer.append(-1)
# for i in answer:
#     print(i)

# --------------------------------------------------

# 2346. 풍선 터뜨리기
'''
    문제풀이방법 및 접근
'''
# from collections import deque
# n = int(input())
# origin = [list(i) for i in enumerate(map(int, input().split()))]
# balloons = deque(origin[:])
# answer = list()
# while True:
#     idx, number = balloons.popleft()
#     answer.append(idx+1)
#     if not balloons:
#         break
#
#     if number > 0:    # 양수이다.
#         for _ in range(number-1):  # 위에서 pop하므로 전체길이는 -1된다.
#             balloons.append(balloons.popleft())
#     elif number < 0:  # 음수이다.
#         for _ in range(abs(number)):
#             balloons.appendleft(balloons.pop())
# for i in answer:
#     print(i, end=' ')

# --------------------------------------------------

# 24511. queuestack
'''
    문제풀이방법 및 접근
        - 질문게시판 참조
        - 시간초과?
'''
import sys
from collections import deque
n = int(sys.stdin.readline())
a = list(map(int, sys.stdin.readline().split()))
b = list(map(int, sys.stdin.readline().split()))
m = int(sys.stdin.readline())
c = list(map(int, sys.stdin.readline().split()))

for i in range(m):
    curr = deque([c[i]])  # 큐스택에 삽입할 값
    for j in range(n):
        curr.appendleft(b[j])
        if a[j] == 0:  # 큐   - FIFO
            b[j] = curr[1]
            curr = deque([curr[0]])
        if a[j] == 1:  # 스택 - LIFO
            b[j] = curr[0]
            curr = deque([curr[1]])
    print('{}'.format(curr[0]), end=' ')