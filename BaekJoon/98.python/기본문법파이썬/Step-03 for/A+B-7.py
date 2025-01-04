import sys
sys.stdin = open('../input.txt', 'r')

n = int(input())

# A+B-7

# for i in range(1, n+1):
#     a, b = map(int, input().split())
#     print('Case #{}: {}'.format(i, a + b))

# A+B-8

for i in range(1, n+1):
    a, b = map(int, input().split())
    print('Case #{}: {} + {} = {}'.format(i, a, b, a+b))