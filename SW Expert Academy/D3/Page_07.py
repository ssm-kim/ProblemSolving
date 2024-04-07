import sys
sys.stdin = open('./public_input.txt', 'r')

# 3131. 100만 이하의 모든 소수
'''
    문제 이해 및 접근방법
'''
# check = [True, True] + [False] * 999999
# sosu = list()
# for i in range(2, 1000001):
#     if not check[i]:
#         sosu.append(str(i))
#         for j in range(i, 1000001, i):
#             check[j] = True
# print(' '.join(sosu))

# ----------------------------------------------------------------------

# 3260. 두 수의 덧셈
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     a, b = map(int, input().split())
#     print('#{} {}'.format(t, a+b))

# ----------------------------------------------------------------------

# 15230. 알파벳 공부
'''
    문제 이해 및 접근방법
        - 순서는 a부터 순서대로 일치하는 알파벳 개수를 계산하여야 한다.
'''
tc = int(input())
for t in range(1, tc+1):
    s = int(input())

    break


# ----------------------------------------------------------------------\