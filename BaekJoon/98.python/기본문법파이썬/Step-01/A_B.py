import sys
sys.stdin = open('../input.txt', 'r')

a = int(input())
b = int(input())

b_copy = b
print(a * (b_copy % 10))
b_copy -= b_copy % 10 # b = 380
print((a * (b_copy % 100)) // 10)
b_copy -= b_copy % 100 # b = 300
print((a * b_copy) // 100)
print(a * b)