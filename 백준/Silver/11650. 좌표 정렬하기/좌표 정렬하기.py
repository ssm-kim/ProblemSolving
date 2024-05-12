import sys

n = int(sys.stdin.readline())
ch_list = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
ch_list.sort(key=lambda x: (x[0], x[1]))
for i in ch_list:
    print(' '.join(map(str, i)))