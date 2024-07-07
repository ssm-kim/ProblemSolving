import sys
sys.stdin = open('../input.txt', 'r')

x, y, z = map(int, input().split())
total = 0

if x == y == z:
    total = 10000 + (x * 1000)
else:
    if x == y or x == z:
        total = 1000 + (x * 100)
    elif y == z:
        total = 1000 + (y * 100)
    else:
        total = max(x, y, z) * 100
print(total)