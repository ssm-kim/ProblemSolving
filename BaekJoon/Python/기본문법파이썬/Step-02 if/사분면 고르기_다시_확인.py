import sys
sys.stdin = open('../input.txt', 'r')

x = int(sys.stdin.readline())
y = int(sys.stdin.readline())

if x > 0:
    if y > 0:
        print(1)
    else:
        print(4)
else:
    if y < 0:
        print(3)
    else:
        print(2)