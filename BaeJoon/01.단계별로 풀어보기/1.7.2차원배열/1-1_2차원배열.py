import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 2738. 행렬덧셈
'''
    문제풀이방법 및 접근
'''
# n, m = map(int, input().split())
# a = [list(map(int, input().split())) for _ in range(n)]
# b = [list(map(int, input().split())) for _ in range(n)]
# answer = [[0]*m for _ in range(n)]

# for i in range(n):
#     for j in range(m):
#         answer[i][j] = a[i][j] + b[i][j]

# for i in answer:
#     print(' '.join(map(str, i)))

# --------------------------------------------------

# 2566. 최댓값

# board = [list(map(int, input().split())) for _ in range(9)]
# total = list()
# for row in board:
#     total += row
# maximumValue = max(total)

# for i in range(9):
#     if maximumValue in board[i]:
#         row, col = i+1, board[i].index(maximumValue)+1
#         break
# print('{}\n{} {}'.format(maximumValue, row, col))

# --------------------------------------------------

# 10798. 세로읽기
'''
    문제풀이방법 및 접근
'''
# board = [list(input()) for _ in range(5)]
# answer = ''
# maxLength = 0
# for i in board:
#     maxLength = max(maxLength, len(i))

# for i in range(maxLength):
#     for j in range(5):
#         try:
#             answer += board[j][i]
#         except:
#             continue
# print(answer)

# --------------------------------------------------

# 2563. 색종이
'''
    문제풀이방법 및 접근
'''