import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D1
      - 참여자순 
      - 1페이지 단위 ( 10문제 )
    
    구분선을 기준으로 1문제씩 실행.
    
'''

# 2072. 홀수만 더하기
# tc = int(input())

# for t in range(tc):
#   num_list = list(map(int, input().split()))
#   answer = 0

#   for i in num_list:
#     if i%2 == 1:
#       answer += i

#   print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2071. 평균값 구하기
# tc = int(input())
  
# for t in range(1, tc + 1):
#     num_list = list(map(int, input().split()))
#     answer = sum(num_list)/10
    
#     print('#{} {:.0f}'.format(t, answer))

# ----------------------------------------------------------------------

# 2070. 큰 놈, 작은 놈, 같은 놈
# tc = int(input())

# for t in range(1, tc+1):
#     num1, num2 = map(int, input().split())
    
#     if num1 > num2:
#         answer = '>'
#     elif num1 < num2:
#         answer = '<'
#     else:
#         answer = '='

#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2063. 중간값 찾기
# n = int(input())
# num_list = list(map(int, input().split()))

# num_list.sort()

# print(num_list[n//2])

# ----------------------------------------------------------------------

# 2058. 자릿수 더하기
# n = int(input())
# remainder = 0

# while n > 0:
#     remainder += (n % 10)
#     n = n // 10

# print(remainder)

# ----------------------------------------------------------------------

# 2068. 최대수 구하기
# tc = int(input())
# for t in range(1, tc+1):
#     num_list = list(map(int, input().split()))
#     print('#{} {}'.format(t, max(num_list)))

# ----------------------------------------------------------------------

# 1545. 거꾸로 출력해 보아요
# n = int(input())
# for i in range(n, -1, -1):
#     print(i, end=' ')

# ----------------------------------------------------------------------

# 2046. 스탬프 찍기
# n = int(input())
# print('#' * n)

# ----------------------------------------------------------------------

# 2056. 연월일 달력 

# calendar = {
#     1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30,
#     7: 31, 8: 31, 9: 30, 10: 31, 11: 30, 12: 31
# }

# tc = int(input())
# for t in range(1, tc+1):
#     n = input()
#     year, month, day = n[:4], n[4:6], n[6:]

#     if 1 <= int(month) <= 12 and int(day) <= calendar[int(month)]:
#         answer = year + '/' + month + '/' + day
#     else:
#         answer = -1
#     print('#{} {}'.format(t, answer))


# ----------------------------------------------------------------------

# 2047. 신문 헤드라인
# newsPaper = input()
# print(newsPaper.upper())


