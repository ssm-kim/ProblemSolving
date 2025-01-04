import sys
from collections import deque

n = int(sys.stdin.readline())
dq = deque()
for _ in range(n):
    num_list = sys.stdin.readline().rstrip().split()
    if num_list[0] == 'push':
        dq.appendleft(num_list[1])
    elif num_list[0] == 'pop':
        if dq:
            print(dq.pop())
        else:
            print(-1)
    elif num_list[0] == 'size':
        print(len(dq))
    elif num_list[0] == 'empty':
        if not dq:
            print(1)
        else:
            print(0)
    elif num_list[0] == 'front':
        if dq:
            print(dq[-1])
        else:
            print(-1)
    else:
        if dq:
            print(dq[0])
        else:
            print(-1)