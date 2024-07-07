import sys
sys.stdin = open('../input.txt', 'r')

hour, minute = map(int, sys.stdin.readline().split())

# 내 풀이
# if hour == 0:
#     if minute > 45:
#         print(hour, minute-45)
#     elif minute == 45:
#         print(hour, 0)
#     else:
#         print(24 + hour - 1, 60 + minute - 45)
# else:
#     if minute > 45:
#         print(hour, minute-45)
#     elif minute == 45:
#         print(hour, 0)
#     else:
#         print(hour - 1, 60 + minute - 45)
# hour, minute = map(int, input().split())


# 풀이법 본 풀이
# if minute - 45 < 0:
#     minute = 60 - abs(minute-45)
#     if hour > 0:
#         hour -= 1
#     else:
#         hour = 23
#     print(hour, minute)
# else:
#     print(hour, minute-45)