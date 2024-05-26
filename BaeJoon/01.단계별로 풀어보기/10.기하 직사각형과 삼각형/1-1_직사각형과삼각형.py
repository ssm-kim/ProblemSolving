import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 27323. 직사각형 
'''
    문제풀이방법 및 접근
'''
# a = int(input())
# b = int(input())

# print(a * b)

# --------------------------------------------------

# 1085. 직사각형에서 탈출
'''
    문제풀이방법 및 접근
'''
# x, y, w, h = map(int, input().split())

# print(min(w-x, h-y, x, y))

# --------------------------------------------------

# 3009. 네번째 점
'''
    문제풀이방법 및 접근
'''
x = []
y = []
for _ in range(3):
    a, b = map(int, input().split())
    x.append(a)
    y.append(b)

for i in range(3):
    if x.count(x[i]) == 1:
        x1 = x[i]
    if y.count(y[i]) == 1:
        y1 = y[i]
print(x1, y1)

# --------------------------------------------------