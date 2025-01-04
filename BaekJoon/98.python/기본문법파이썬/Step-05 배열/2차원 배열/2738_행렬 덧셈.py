import sys
sys.stdin = open('../input.txt', 'r')

n, m = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(n)]
B = [list(map(int, input().split())) for _ in range(n)]
res = list()

for x, y in zip(A, B):
    tmp = list()
    for i in range(m):
        tmp += list([x[i] + y[i]])
    res.append(tmp)
for i in res:
    print(' '.join(map(str, i)))
