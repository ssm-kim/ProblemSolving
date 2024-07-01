import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 참여자순 
      - 페이지 단위 ( 10문제 )
'''

# 1966. 숫자를 정렬하자
# 구현
# 1번 방법
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     num = list(map(int, input().split()))
#     for i in range(n):
#         for j in range(n):
#             if num[i] < num[j]:             
#                 tmp = num[i]
#                 num[i] = num[j]
#                 num[j] = tmp
#     print('#{} {}'.format(t, ' '.join(map(str, num))))

# 2번 방법
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     num = list(map(int, input().split()))
#     answer = list()
#     while num:
#         check = max(num)
#         for i in range(len(num)):
#             if num[i] < check:
#                 check = num[i]
#         rmValue = num.pop(num.index(check))
#         answer.append(rmValue)
#     print('#{} {}'.format(t, ' '.join(map(str, answer))))

# ----------------------------------------------------------------------

# 1946.간단한 압축 풀기
# 리스트 슬라이싱 활용

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     s = ''
#     for i in range(n):
#         c, k = input().split()
#         s += c * int(k)
    
#     print('#{}'.format(t))
#     for i in range(len(s)//10 + 1):
#         print(s[:10])
#         s = s[10:]

# ----------------------------------------------------------------------

# 1940. 가랏! RC카!
# 구현
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     command = [list(map(int, input().split())) for _ in range(n)]
#     distance, curSpeed = 0, 0
    
#     for x in command:
#         if len(x) == 1: # 현재 속도 유지
#             distance += curSpeed
#             continue

#         time, speed = x
#         if time == 1:   # 가속
#             curSpeed += speed
#         else:           # 감속
#             curSpeed -= speed
#             if curSpeed < 0:
#                 curSpeed = 0
#         distance += curSpeed
        
#     print('#{} {}'.format(t, distance))    

# ----------------------------------------------------------------------

# 1976. 시각 덧셈
# 구현
# tc = int(input())
# for t in range(1, tc+1):
#     hour1, minutes1, hour2, minutes2 = map(int, input().split())

#     hour = hour1 + hour2
#     minute = minutes1 + minutes2

#     if minute >= 60:
#         hour += (minute//60)
#         minute -= 60 * (minute//60)
#     print(hour, minute)
#     if hour >= 13:
#         hour -= 12

#     print('#{} {} {}'.format(t, hour, minute))

# ----------------------------------------------------------------------

# 1948. 날짜 계산기
# 구현
# tc = int(input())
# for t in range(1, tc+1):
#     calendar = { 1:31, 2:28, 3:31, 4:30, 5:31, 6:30,
#                  7:31, 8:31, 9:30, 10:31, 11:30, 12:31 }
#     month1, day1, month2, day2 = map(int, input().split())
#     answer = 1
#     if month1 == month2:
#         answer += (calendar[month1] - day1)
#     else:
#         answer += (calendar[month1] - day1) + day2
#     for i in range(month1+1, month2):
#         answer += calendar[i]
#     print('#{} {}'.format(t, answer))    