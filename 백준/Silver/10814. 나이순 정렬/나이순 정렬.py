import sys

n = int(sys.stdin.readline().rstrip())
members = list(sys.stdin.readline().rstrip().split() for _ in range(n))
members.sort(key=lambda x: int(x[0]))
for i in members:
    print(' '.join(map(str, i)))