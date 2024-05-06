import sys
sys.stdin = open('../input.txt', 'r')

n = int(input())

for i in range(n):
    a, b = map(int, input().split())
    print(a + b)