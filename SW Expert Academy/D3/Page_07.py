import sys
sys.stdin = open('./public_input.txt', 'r')

# 3131. 100만 이하의 모든 소수
'''
    문제 이해 및 접근방법
'''
# check = [True, True] + [False] * 999999
# sosu = list()
# for i in range(2, 1000001):
#     if not check[i]:
#         sosu.append(str(i))
#         for j in range(i, 1000001, i):
#             check[j] = True
# print(' '.join(sosu))

# ----------------------------------------------------------------------

# 3260. 두 수의 덧셈
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     a, b = map(int, input().split())
#     print('#{} {}'.format(t, a+b))

# ----------------------------------------------------------------------

# 15230. 알파벳 공부
'''
    문제 이해 및 접근방법
        - 순서는 a부터 순서대로 일치하는 알파벳 개수를 계산하여야 한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     alpha = [chr(i) for i in range(97, 123)]
#     cnt = 0
#     for i in range(len(s)):
#         if s[i] == alpha[i]:
#             cnt += 1
#         else:
#             break
#     print('#{} {}'.format(t, cnt))

# ----------------------------------------------------------------------

# 4047. 영준이의 카드 카운팅
'''
    문제 이해 및 접근방법
     - '한 덱'이란 스페이드, 다이아몬드, 하트, 클로버 무늬 별로 각각 A, 2~10, J, Q, K의 라벨 즉 4개의 무늬 별로
        각각 13장씩 총 52장의 카드가 있는 모음을 의미한다.
     - 편의상 A는 1, J, Q, K는 11, 12, 13으로 하여 1~13의 숫자가 카드에 적혀있다
     - 게임을 하기 위해서 몇 장의 카드가 더 필요한지 알고 싶어 한다.
     - 이미 겹치는 카드를 가지고 있다면 오류를 출력.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     cards = {'S':list(range(1, 14)), 'D':list(range(1, 14)), 'H':list(range(1, 14)), 'C':list(range(1, 14))}
#     answer =  ''
#     for i in range(0, len(s), 3):
#         tmp = s[i:i+3]
#         key, num = tmp[0], int(''.join(tmp[1:]))
#         if cards[key]:
#             if num in cards[key]:
#                 cards[key].remove(num)
#             else: # 겹치는 카드 존재
#                 answer = 'ERROR'
#                 break
#     if answer:
#         print('#{} {}'.format(t, answer))
#     else:
#         print('#{}'.format(t), end=' ')
#         for i in cards.values():
#             print(len(i), end=' ')
#         print()

# ----------------------------------------------------------------------

# 10761. 신뢰  >  좋은 문제인지 모르겠음. 설명이 부족함. (DFS)
'''
    문제 이해 및 접근방법
'''

# ----------------------------------------------------------------------