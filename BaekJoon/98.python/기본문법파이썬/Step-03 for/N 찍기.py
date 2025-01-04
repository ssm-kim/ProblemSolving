import sys
sys.stdin = open('../input.txt', 'r')

# n = int(input())
# for i in range(1, n+1):
#     print(i)

# 뒤집기
n = int(input())
for i in range(-n, 0):
    print(abs(i))