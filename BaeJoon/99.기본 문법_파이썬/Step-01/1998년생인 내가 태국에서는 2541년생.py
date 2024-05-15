import sys
sys.stdin = open('../input.txt', 'r')

# print(int(input())-543)

# 1. N에서 1을 뺀다
# 2. N을 K로 나눈다.
# 그래서 값이 N이 1이 될때까지의 최소 횟수를 구하라.

n, k = map(int, input().split())

cnt = 0
while n != 1:
    if n % k == 0:
        n = n//k
        cnt += 1
    else:
        n -= 1
        cnt += 1 # 1번 횟수
print(cnt)