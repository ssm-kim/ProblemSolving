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
# x = []
# y = []
# for _ in range(3):
#     a, b = map(int, input().split())
#     x.append(a)
#     y.append(b)

# for i in range(3):
#     if x.count(x[i]) == 1:
#         x1 = x[i]
#     if y.count(y[i]) == 1:
#         y1 = y[i]
# print(x1, y1)

# --------------------------------------------------

# 15894. 수학은 체육과목 입니다
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# print(n*4)

# --------------------------------------------------

# 9063. 대지
'''
    문제풀이방법 및 접근
        - (x의 가장큰값 - x의 가장작은값) * (y의 가장큰값 - y의 가장작은값)
        - 초기값 세팅을 0, 10001로 하면 안됨.
'''
# n = int(input())
# coordinate = [list(map(int, input().split())) for _ in range(n)]

# if n == 1:
#     answer = 0
# else:
#     row, col = list(), list()
#     for x, y in coordinate:
#         row.append(x)
#         col.append(y)
    
#     answer = (max(row)-min(row)) * (max(col)-min(col))
# print(answer)

# --------------------------------------------------

# 10101. 삼각형 외우기
'''
    문제풀이방법 및 접근
        - 세 각의 크기가 모두 60이면, Equilateral
        - 세 각의 합이 180이고, 두 각이 같은 경우에는 Isosceles
        - 세 각의 합이 180이고, 같은 각이 없는 경우에는 Scalene
        - 세 각의 합이 180이 아닌 경우에는 Error
'''
# a = int(input())
# b = int(input())
# c = int(input())
# answer = ''
# if a == 60 and b == 60 and c == 60:
#     answer = 'Equilateral'
# elif (a+b+c == 180) and (a == b or b == c or a == c):
#     answer = 'Isosceles'
# elif (a+b+c == 180) and a != b and b != c:
#     answer = 'Scalene'
# else:
#     answer = 'Error'
# print(answer)

# --------------------------------------------------

# 5073. 삼각형과 세 변
'''
    문제풀이방법 및 접근
'''
# while True:
#     a, b, c = map(int, input().split())
#     if a==0 and b==0 and c==0:
#         break

#     answer = ''
#     tmp = [a, b, c]
#     longest = max(tmp)
#     tmp.remove(longest)

#     if longest >= sum(tmp):
#         answer = 'Invalid'
#     elif a==b and b==c:
#         answer = 'Equilateral'
#     elif a==b or b==c or a==c:
#         answer = 'Isosceles'
#     elif a!=b and b!=c and a!=c:
#         answer = 'Scalene'
#     print(answer)

# --------------------------------------------------

# 14215. 세 막대
'''
    문제풀이방법 및 접근
        -삼각형의 필수 조건에 의해 작은 두 수의 합이 가장 큰 수보다 크다면 전체의 합을 출력
        - 작다면 작은 두 수의 합의 두배에서 1을 뺀 값을 출력한다.
'''
length = list(map(int, input().split()))
length.sort()
a, b, c = length

if a+b > c:
    answer = a+b+c
else:
    c = a+b-1
    answer = a+b+c
print(answer)