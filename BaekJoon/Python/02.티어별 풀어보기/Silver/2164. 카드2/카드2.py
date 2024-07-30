from collections import deque
n = int(input())
queue = deque(i for i in range(1, n+1))

while True:
    if len(queue) == 1:
        print(queue[0])
        break
    queue.popleft()  # 카드를 버린다.
    mv = queue.popleft()  # 첫번째 카드를 마지막 순서로 옮긴다.
    queue.append(mv)