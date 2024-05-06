import sys
sys.stdin = open('../input.txt', 'r')

a, b = sys.stdin.readline().split()
first_num, second_num = '', ''
for i in range(-1, -len(a)-1, -1):
    first_num += a[i]
    second_num += b[i]
if first_num > second_num:
    print(first_num)
else:
    print(second_num)


# a, b = input().split()
# first_num = ''
# second_num = ''
# for i in range(1, len(a)+1):
#     first_num += a[-i]
#     second_num += b[-i]
#
# if first_num > second_num:
#     print(first_num)
# else:
#     print(second_num)