import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 2798. 블랙잭
'''
    문제풀이방법 및 접근
'''
# n, m = map(int, input().split())
# card_num = list(map(int, input().split()))
# answer = 0
# for i in range(n-2):
#     for j in range(i+1, n-1):
#         for k in range(j+1, n):
#             sum = card_num[i] + card_num[j] + card_num[k]
#             if sum <= m:
#                 answer = max(answer, sum)
# print(answer)    

# --------------------------------------------------

# 2231. 분해합
'''
    문제풀이방법 및 접근
'''
# def check(num):
#     total = num
#     while num > 0:
#         value = num % 10  # 나머지
#         num //= 10          # 몫
#         total += value
#     return total
    
# n = int(input())
# for i in range(1, n+1):
#     divide_sum = check(i)
#     if divide_sum == n:  # 가장 작은 생성자를 구해야하므로 찾으면 바로 반복문 중지
#         print(i)
#         break
# else:  # 만약 생성자가 없다면 0 출력
#     print(0)

# --------------------------------------------------
# 19532. 수학은 비대면강의입니다
'''
    문제풀이방법 및 접근
        - 모든 경우의 수 탐색
'''
# a, b, c, d, e, f = map(int, input().split())
# for i in range(-999, 1000):
#     for j in range(-999, 1000):
#         if (a*i) + (b*j) == c and (d*i) + (e*j) == f:
#             print(i, j)
#             break

# --------------------------------------------------

# 1018. 체스판 다시 칠하기
'''
    문제풀이방법 및 접근
        - 모든 경우의 수 탐색
'''
n, m = map(int, input().split())
board = [list(input()) for _ in range(n)]
answer = 100000000  # 다시 칠해야하는 정사각형 최소갯수
for i in range(n-8+1):
    for j in range(m-8+1):
        cnt1, cnt2 = 0, 0
        for k in range(i, i+8):
            for l in range(j, j+8):
                curr = board[k][l]
                if (k+l) % 2 == 0:  # 짝수일 때
                    if curr == 'W':
                        cnt1 += 1
                    else:
                        cnt2 += 1
                else:  # 홀수일 때
                    if curr == 'B':
                        cnt1 += 1
                    else:
                        cnt2 += 1
        answer = min(answer, min(cnt1, cnt2))
print(answer)


# --------------------------------------------------

# 1436. 영화감독숌
'''
    문제풀이방법 및 접근
        - 모든 경우의 수 탐색
'''

# --------------------------------------------------

# 2839. 설탕배달
'''
    문제풀이방법 및 접근
        - 모든 경우의 수 탐색
'''