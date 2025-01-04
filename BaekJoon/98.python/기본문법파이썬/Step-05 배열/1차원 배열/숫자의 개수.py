import sys
sys.stdin = open('../input.txt', 'r')

a = int(input())
b = int(input())
c = int(input())

Total = a * b * c # 17037300
ch = [0] * 10

for _ in range(len(str(Total))):
    s = Total % 10
    Total = Total // 10
    ch[s] += 1

for i in ch:
    print(i)