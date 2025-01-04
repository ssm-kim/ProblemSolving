import sys
sys.stdin = open('../input.txt', 'r')

num = int(input())
Total = 0
for i in range(1, num+1):
    Total += i
print(Total)