import sys
from collections import deque
n = int(input())
dq = deque()
answer = list()
for i in range(n):
    command = list(map(int, sys.stdin.readline().split()))
    if len(command) == 2:
        a, b = command
        if a == 1:
            dq.appendleft(b)
        elif a == 2:
            dq.append(b)
    else:
        command = command.pop()
        if command == 3:
            if dq:
                answer.append(dq.popleft())
            else:
                answer.append(-1)
        elif command == 4:
            if dq:
                answer.append(dq.pop())
            else:
                answer.append(-1)
        elif command == 5:
            answer.append(len(dq))
        elif command == 6:
            if dq:
                answer.append(0)
            else:
                answer.append(1)
        elif command == 7:
            if dq:
                answer.append(dq[0])
            else:
                answer.append(-1)
        elif command == 8:
            if dq:
                answer.append(dq[-1])
            else:
                answer.append(-1)
for i in answer:
    print(i)
