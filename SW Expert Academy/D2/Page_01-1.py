import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 정답율순
      - 1페이지 단위 ( 10문제 )
'''

# 1945. 간단한 소인수분해
# def calculate(num):
#     global n
#     cnt = 0
#     while n%num == 0:
#         cnt += 1
#         n = n / num
#     return str(cnt)

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     div = [2, 3, 5, 7, 11]
#     answer = ''
#     for i in div:
#         answer += calculate(i)
#     print('#{} {}'.format(t, ' '.join(answer)))
    
# ----------------------------------------------------------------------

# 1928.Base64 Decoder  >  Pass

# ----------------------------------------------------------------------

# 1986. 지그재그 숫자
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     answer = 0
#     for i in range(1, n+1):
#         if i%2 == 0:
#             answer -= i
#         else:
#             answer += i
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1288. 새로운 불면증 치료법
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     cnt = n
#     check = list()
#     while len(check) != 10:
#         search = list(str(cnt))
#         for i in search:
#             if i not in check:
#                 check.append(i)
#         cnt = cnt + n
#     print('#{} {}'.format(t, cnt-n))

# ----------------------------------------------------------------------

# 1284. 수도 요금 경쟁
# tc = int(input())
# for t in range(1, tc+1):
#     p, q, r, s, w = map(int, input().split())

#     a = p * w   # A사 수도요금

#     if w <= r:  # B사 수도요금
#         b = q
#     else:
#         b = q + (w-r)*s
    
#     print('#{} {}'.format(t, min(a, b)))

# ----------------------------------------------------------------------

# 1989. 초심자의 회문 검사
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     answer = 1
#     for i in range(len(s)//2):
#         if s[i] != s[-1-i]:
#             answer = 0
#             break
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1959. 두 개의 숫자열
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     a = list(map(int, input().split()))
#     b = list(map(int, input().split()))
#     answer = 0
#     if n < m:
#         rt = m-n
#         for i in range(m-n+1):
#             tmp = 0
#             for j in range(i, m-rt):
#                 tmp += a[j-i] * b[j]
#             answer = max(answer, tmp)
#             rt -= 1
#     else:
#         rt = n-m
#         for i in range(n-m+1):
#             tmp = 0
#             for j in range(i, n-rt):
#                 tmp += b[j-i] * a[j]
#             answer = max(answer, tmp)
#             rt -= 1
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2007. 패턴 마디의 길이
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     for i in range(1, len(s)//2+1):
#         if s[:i] == s[i:i*2]:
#             answer = len(s[:i])
#             break
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1966. 숫자를 정렬하자  >  Pass

# ----------------------------------------------------------------------

# 1948. 날짜 계산기

# tc = int(input())
# calendar = { 1:31, 2:28, 3:31,  4:30,  5:31,  6:30,
#              7:31, 8:31, 9:30, 10:31, 11:30, 12:31 }
# for t in range(1, tc+1):
#     st_m, st_d, end_m, end_d = map(int, input().split())
#     if end_m > st_m:
#         days = (calendar[st_m]-st_d+1) + (end_d)  # 시작달일수 + 마지막달일수
#         for i in range(st_m+1, end_m):
#             days += calendar[i]
#     else:
#         days = end_d-st_d+1

#     print('#{} {}'.format(t, days))
    
