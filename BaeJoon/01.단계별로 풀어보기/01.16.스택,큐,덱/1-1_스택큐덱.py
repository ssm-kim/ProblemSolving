import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''
# 28278. 스택 2
'''
    문제풀이방법 및 접근
'''
# n = int(input())
# commands = [list(map(int, input().split())) for _ in range(n)]
# stack, res = list(), list()
# for command in commands:
#     if len(command) == 2:
#         a, b = command
#         stack.append(b)
#     else:
#         x = command[0]
#         if x == 2:
#             if stack:
#                 res.append(stack.pop())
#             else:
#                 res.append(-1)
#         elif x == 3:
#             res.append(len(stack))
#         elif x == 4:
#             if stack:
#                 res.append(0)
#             else:
#                 res.append(1)
#         else:
#             if stack:
#                 res.append(stack[-1])
#             else:
#                 res.append(-1)
# for i in res:
#     print(i)

# --------------------------------------------------

# 10773. 제로
'''
    문제풀이방법 및 접근
'''
# k = int(input())
# stack = list()
# for i in range(k):
#     num = int(input())
#     if num == 0:
#         stack.pop()
#     else:
#         stack.append(num)
# print(sum(stack))

# --------------------------------------------------

# 9012. 괄호 
'''
    문제풀이방법 및 접근
'''
# tc = int(input())
# for _ in range(tc):
#     st = list(input())
#     stack = list()
#     answer = 'YES'
#     for i in st:
#         if i == '(':
#             stack.append(i)
#         elif i == ')':
#             if stack and (stack[-1] == '('):
#                 stack.pop()
#             else:
#                 stack.append(i)
#     if stack:
#         answer = 'NO'
#     print(answer)

# --------------------------------------------------

# 4949. 균형잡힌 세상 
'''
    문제풀이방법 및 접근
'''
while True:
    check_st = input()
    if check_st == '.':
        break
    
    check_st = list(check_st)
    stack, answer = list(), 'yes'
    
    for i in range(len(check_st)):
        st = check_st[i]
        if st == '(' or st == '[':
            stack.append(st)
        else:
            if st == ')':
                if stack and stack[-1] == '(':
                    stack.pop()
                else:
                    stack.append(st)
            elif st == ']':
                if stack and stack[-1] == '[':
                    stack.pop()
                else:
                    stack.append(st)

    if stack:
        answer = 'no'
    print(answer)

# --------------------------------------------------

# 12789.도키도키 간식드리미
'''
    문제풀이방법 및 접근
'''


# --------------------------------------------------