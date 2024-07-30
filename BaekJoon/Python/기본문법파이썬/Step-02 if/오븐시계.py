import sys
sys.stdin = open('../input.txt', 'r')

a, b = map(int, input().split())
c = int(input())
minute = b + c

while minute >= 60:
    minute -= 60
    a += 1

if a > 23:
    a -= 24

print(a, minute)