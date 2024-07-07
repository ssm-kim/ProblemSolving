import sys
tc = int(sys.stdin.readline())
n = [0] * 10001
for _  in range(tc):
    n[int(sys.stdin.readline())] += 1

for i in range(1, 10001):
    for j in range(n[i]):
        print(i)
