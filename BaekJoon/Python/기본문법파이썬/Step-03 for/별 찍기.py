import sys
sys.stdin = open('../input.txt', 'r')


# 별 찍기 1번
# n = int(input())
#
# for i in range(1, n+1):
#     print('*' * i)

# 별 찍기 2번
n = int(input())

for i in range(1, n+1):
    print(' ' * (n-i) + '*' * i)