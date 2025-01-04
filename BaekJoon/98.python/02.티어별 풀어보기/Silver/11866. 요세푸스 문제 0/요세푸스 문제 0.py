from collections import deque

n, k = map(int, input().split())
queue = deque(i for i in range(1, n+1))
answer = list()  # 요세푸스 순열
idx = 0          # 인덱스 번호
while queue:
    idx += 1
    if idx == k:
        idx = idx % k
        answer.append(queue.popleft())
        continue
    queue.append(queue.popleft())

print('<{}>'.format(', '.join(map(str, answer))))