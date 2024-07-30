import sys
sys.stdin = open('../input.txt', 'r')

Student_list = [int(input()) for _ in range(28)]
ch_list = list(range(1, 31))
for i in Student_list:
    ch_list.remove(i)

print('\n'.join(map(str, ch_list)))