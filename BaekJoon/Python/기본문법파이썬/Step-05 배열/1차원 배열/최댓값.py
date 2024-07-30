import sys
sys.stdin = open('../input.txt', 'r')

a = list()
while True:
    try:
        a.append(int(input()))
    except:
        break

_max = -1000000
for i in a:
    if i > _max:
        _max = i

print(_max)
print(a.index(_max)+1)