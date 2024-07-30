import sys
sys.stdin = open('../input.txt', 'r')
for _ in range(int(sys.stdin.readline())):
    print(sum(map(int, sys.stdin.readline().split())))