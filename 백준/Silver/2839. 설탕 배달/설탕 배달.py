n = int(input())
three = 0
while (n % 5 != 0) and (n >= 3):
    three += 1
    n -= 3

if n % 5 == 0:
    five = n // 5
    answer = three + five
else:
    answer = -1
print(answer)