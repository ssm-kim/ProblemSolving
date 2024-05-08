n, b = map(int, input().split())
num_list = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
answer = ''
while n > 0:
    remain = (n % b)
    n = n // b
    answer = num_list[remain] + answer
print(answer)
