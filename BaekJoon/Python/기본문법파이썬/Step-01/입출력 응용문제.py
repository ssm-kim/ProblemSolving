import sys
sys.stdin = open('../input.txt', 'r')

ch_list = list([1, 1, 2, 2, 2, 8])
tk = list(map(int, sys.stdin.readline().rstrip().split()))
res = list()
cnt = 0
for ch, t in zip(ch_list, tk):
    while ch != t:
        if ch > t:
            cnt += 1
            t += 1
        else:
            cnt -= 1
            t -= 1
    res.append(cnt)
    cnt = 0
print(' '.join(map(str, res)))