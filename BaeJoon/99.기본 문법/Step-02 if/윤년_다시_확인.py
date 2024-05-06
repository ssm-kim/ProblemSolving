import sys
sys.stdin = open('../input.txt', 'r')

n = int(sys.stdin.readline())

# 윤년 --> 4의 배수 이면서 100의 배수가 아닐 떄
# 윤년 --> 4의 배수 이면서 400의 배수가 일 떄

if (n % 4 == 0) and (n % 100 != 0 or n % 400 == 0):
    print(1)
else:
    print(0)