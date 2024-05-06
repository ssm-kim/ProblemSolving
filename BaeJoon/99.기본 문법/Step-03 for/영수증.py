import sys
sys.stdin = open('../input.txt', 'r')

all_price = int(input())
n = int(input())
total_price = 0
for _ in range(n):
    product_price, cnt = map(int, input().split())
    total_price += (product_price * cnt)

if total_price == all_price:
    print('Yes')
else:
    print('No')