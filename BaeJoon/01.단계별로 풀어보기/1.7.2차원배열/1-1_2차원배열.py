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
        - 100*100 2차원배열에서 범위에 포함되는 인덱스 값을 1로 변경 후 전체 합을 구한다.
'''

# rectangle = [[0]*100 for _ in range(100)]
# n = int(input())
# tc = [list(map(int, input().split())) for _ in range(n)]
# for x, y in tc:
#     row1, row2, col1, col2 = x, x+10, y, y+10 
#     for i in range(row1, row2):
#         for j in range(col1, col2):
#             rectangle[i][j] = 1
# area = 0
# for i in rectangle:
#     area += sum(i)
# print(area)
    