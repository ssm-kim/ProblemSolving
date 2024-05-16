from collections import deque
n = int(input())
origin = [list(i) for i in enumerate(map(int, input().split()))]
balloons = deque(origin[:])
answer = list()
while True:
    idx, number = balloons.popleft()
    answer.append(idx+1)
    if not balloons:
        break

    if number > 0:    # 양수이다.
        for _ in range(number-1):  # 위에서 pop하므로 전체길이는 -1된다.
            balloons.append(balloons.popleft())
    elif number < 0:  # 음수이다.
        for _ in range(abs(number)):
            balloons.appendleft(balloons.pop())
for i in answer:
    print(i, end=' ')