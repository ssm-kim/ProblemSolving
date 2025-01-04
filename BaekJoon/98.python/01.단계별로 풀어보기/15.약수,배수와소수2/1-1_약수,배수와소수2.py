import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''
# 1934. 최소공배수 
'''
    문제풀이방법 및 접근
        - 변수 생성 후 각각의 숫자를 배수하면서 비교한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     a, b = map(int, input().split())
#     val1, val2 = a, b
#     num1, num2 = 1, 1       # 곱하는 숫자
#     while True:   # 최소공배수를 찾을때까지 반복
#         if val1 == val2:
#             answer = val1
#             break
#         elif val1 < val2:
#             val1 = num1 * a  # a의 배수
#             num1 += 1
#         else:
#             val2 = num2 * b  # b의 배수
#             num2 += 1            
#     print(answer)

# --------------------------------------------------

# 13241. 최소공배수 
'''
    문제풀이방법 및 접근
        - GCD 공식 활용
'''
a, b = map(int, input().split())
