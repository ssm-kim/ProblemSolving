import sys
sys.stdin = open('../input.txt', 'r')

# 내 풀이 수식만 사용
n = int(sys.stdin.readline())
n_copy = n
new_num = 100
sum_cycle = 0
while n_copy != new_num:
    remainder = n % 10
    n = n // 10
    if (n+remainder) >= 10:
        new_num = remainder*10 + (n + remainder) % 10
    else:
        new_num = remainder*10 + (n+remainder)
    n = new_num
    sum_cycle += 1
print(sum_cycle)

# 수식 + 문자열 같이 사용
# n = int(input())
# first = n
# cnt = 0
# new_num = 100
#
# while first != new_num:
#     a = n % 10 # 8
#     n = n // 10 # 6
#     _sum = n + a #  14
#     n = int(str(a) + str(_sum)[-1])
#     cnt += 1
#     new_num = n
# print(cnt)