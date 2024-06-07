import sys

t = int(sys.stdin.readline().rstrip())

for _ in range(t):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    _min = a * b
    num1, num2 = a, b
    cnt = min(a, b) # 6
    while cnt <= _min: # 8 / 10
        if num1 < num2: # 12 10 / 18 20
            num1 += a
        elif num1 == num2:
            if _min % num1 == 0:
                _min = num1
                break
        else: # 12 20
            num2 += b
        cnt += 1
    print(_min)