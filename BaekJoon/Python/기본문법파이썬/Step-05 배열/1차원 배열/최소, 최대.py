import sys
sys.stdin = open('../input.txt', 'r')

n = int(input())
a = list(map(int, input().split()))
_min = 10000000
_max = -10000000
for i in a:
    if i < _min:
        _min = i
    if i > _max:
        _max = i
print(_min, _max)