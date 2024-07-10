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

# 10726. 이진수 표현
'''
    문제 이해 및 접근방법
     - m을 이진수로 변경한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     answer, binary = 'OFF', ''

#     while m > 0:
#         binary = str(m%2) + binary
#         m //= 2
    
#     if binary[-n:].count('1') == n:
#         answer = 'ON'
    
#     print('#{} {}'.format(t, answer))
    
# ----------------------------------------------------------------------

# 11387. 몬스터 사냥
'''
    문제 이해 및 접근방법
     - 용사가 몬스터를 공격할 때는 기본적으로 D만큼의 데미지를 입힌다. 
     - 추가데미지는 지금까지 몬스터를 때린 횟수가 n번이라고 하면,
       다음 공격이 몬스터에게 입히는 데미지는 D(1+nㆍL%)가 된다. 
'''
# tc = int(input())
# for t in range(1, tc+1):
#     d, l, n = map(int, input().split())
#     damage = 0
#     for i in range(n):
#         damage += d * (1 + i * (l * 0.01))
    
#     print('#{} {}'.format(t, int(damage)))

# ----------------------------------------------------------------------

# 20019. 회문의 회문
'''
    문제 이해 및 접근방법
    - S는 회문이다.
    - S의 처음 (N-1)/2글자가 회문이다.
    - S의 마지막 (N-1)/2글자가 회문이다.
    - 주어진 문자열이 회문의 회문인지 판별하는 프로그램을 작성하라.
'''
# def palindrome(s):
#     for i in range(len(s)//2):
#         if s[i] != s[len(s)-i-1]:
#             return False
#     return True

# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     answer = 'NO'
#     if palindrome(s):  # S는 회문인지 확인
#         if palindrome(s[:len(s)//2]) and palindrome(s[len(s)//2+1:]):
#             answer = 'YES'
#     print('#{} {}'.format(t, answer))
    
# ----------------------------------------------------------------------

# 3307. 최장 증가 부분 수열  >  DFS?
'''
    문제 이해 및 접근방법
'''

# ----------------------------------------------------------------------

# 16800. 구구단 걷기
'''
    문제 이해 및 접근방법
     - 처음 위치 좌표는 (1, 1) 고정
     - 그림을 그려서 로직확인
     - root값 즉, 정사각형에 가까울수록 거리가 짧아짐.
     - 가장 적은 이동을 할 수 있다면 몇 칸을 이동하는지를 출력
    * 참조 - https://velog.io/@dyd1308/SWEA-D3-16800.-%EA%B5%AC%EA%B5%AC%EB%8B%A8-%EA%B1%B7%EA%B8%B0-python
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())  # n의 루트값 활용
#     sa, sb = 1, 1  # 시작점 a, b
    
#     for div in range(int(n**(1/2)), 0, -1):
#         if (n % div) == 0:
#             i, j = div, (n//div)
#             break
#     print('#{} {}'.format(t, (i-1)+(j-1)))