import sys
sys.stdin = open('../input.txt', 'r')

n = int(input())
num_list = list(map(int, input().split()))
v = int(input())

if v in num_list:
    print(num_list.count(v))
else:
    print(0)