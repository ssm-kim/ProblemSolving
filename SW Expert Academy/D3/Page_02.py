import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D3 정렬조건
        - 정답율순, Python
        - 1페이지 단위 ( 10문제 )
      * 너무 쉬운 문제는 Pass

    풀이방법
        1. 문제 이해하기
            - input, output 특징 확인, input size N (시간복잡도 계산)
            - 제약조건 확인 (시간복잡도 제한 체크 및 알고리즘 선택) 및 예상할 수 있는 오류 파악

        2. 접근 방법
            2-1. 직관적으로 생각하기
                - 보통 완전탐색으로 시작
                - 문제 상황을 단순화/극한화하여 생각하기         
            2-2. 자료구조와 알고리즘 활용
            3-3. 메모리 사용
                - 시간복잡도를 줄이기 위해 사용하는 방법
                - 대표적으로 해시테이블 (딕셔너리)

        3. 코드 설계
        4. 코드 구현
'''

# # 10570. 제곱 팰린드롬 수
'''
     문제 이해 및 접근방법
        - 앞으로 읽어도 뒤로 읽어도 똑같은 문자열을 팰린드롬 혹은 회문
        - 어떠한 양의 정수 N에 대해서, N과 √N이 모두 팰린드롬이면 이 수를 제곱 팰린드롬 수
'''
# def palindrome(s):
#     for i in range(len(s)//2):
#         if s[i] != s[-1-i]:
#             return False
#     return True

# tc = int(input())
# for t in range(1, tc + 1):
#     a, b = map(int, input().split())
#     cnt = 0
#     for i in range(a, b+1):
#         square = int(i**(1/2))
#         if i == (square**2):
#             ch1 = palindrome(str(square))
#             ch2 = palindrome(str(i))
#             if ch1 and ch2:
#                 cnt += 1 
#     print('#{} {}'.format(t, cnt))

# ----------------------------------------------------------------------

# 1215. [S/W 문제해결 기본] 3일차 - 회문1
'''
    문제 이해 및 접근방법
     - 똑바로 읽어도 거꾸로 읽어도 똑같은 문장이나 낱말을 회문(回文, palindrome)
     - 8x8 평면 글자판에서 제시된 길이를 가진 회문의 개수
     - 가로 또는 세로로 이어진 회문의 개수만 센다.
'''
# def palindrome(check):
#     for i in range(len(check)//2):
#         if check[i] != check[-1-i]:
#             return False
#     return True

# tc = 10
# for t in range(1, tc+1):
#     n = int(input())
#     board = [list(input()) for _ in range(8)]
#     cnt = 0
#     for i in range(len(board)):
#         col = 0
#         for j in range(8-n+1):
#             if palindrome(board[i][j:j+n]):  # 가로 n길이 문자수 체크
#                 cnt += 1
#             s = ''
#             for k in range(n):
#                 s += board[k+col][i]  
#             if palindrome(s):  # 세로 n길이 문자수 체크
#                 cnt += 1
#             col += 1
#     print('#{} {}'.format(t, cnt))
    
# ----------------------------------------------------------------------

# 5601. [Professional] 쥬스 나누기
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     print('#{}'.format(t), end=' ')
#     for i in range(1, n+1):
#         print('{}/{} '.format(1, n), end='')
#     print()
    
# ----------------------------------------------------------------------

# 1220.[S/W 문제해결 기본] 5일차 - Magnetic
'''
    문제 이해 및 접근방법
        - 1은 N극 성질을 가지는 자성체를 2는 S극 성질을 가지는 자성체를 의미
        - 자성체는 테이블 앞뒤 쪽에 있는 N극 또는 S극에만 반응하며 자성체끼리는 전혀 반응하지 않는다.
'''
# tc = 10
# for t in range(1, tc+1):
#     n = int(input())
#     board = [list(map(int, input().split())) for _ in range(100)]
#     cnt = 0
#     for i in range(n):
#         cur_state, flag  = '', True
#         for j in range(n):
#             if board[j][i] == 1:  # N극
#                 if flag and cur_state == 2:
#                     cnt += 1
#                 else:
#                     cur_state = 1
#                     flag = True

#             elif board[j][i] == 2:  # S극
#                 if cur_state == 1:  # 교착상태
#                     cnt += 1
#                     cur_state = ''
#                 else:
#                     cur_state = 2
#                     flag = False
    
#     print('#{} {}'.format(t, cnt))

# ----------------------------------------------------------------------
    
# 1225. [S/W 문제해결 기본] 7일차 - 암호생성기
'''
    문제 이해 및 접근방법
'''

# from collections import deque

# tc = 10
# for t in range(1, tc+1):
#     n = int(input())
#     code = deque(map(int, input().split()))
#     minus = 1
#     while True:
#         if code[-1] <= 0:
#             code[-1] = 0
#             break
#         code[0] -= minus
#         code.append(code.popleft())
#         minus += 1
#         if minus == 6:
#             minus %= 5
#    print('#{} {}'.format(t, ' '.join(map(str, code))))

# ----------------------------------------------------------------------
    
# 13229. 일요일
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     weekend = {'MON':1, 'TUE':2, 'WED': 3, 'THU':4, 'FRI':5, 'SAT':6, 'SUN':7}
#     nextSunday = 7
    
#     nextSunday -= weekend[s]
#     if nextSunday == 0:
#         nextSunday = 7

#     print('#{} {}'.format(t, nextSunday))

# ----------------------------------------------------------------------

# 5431.민석이의 과제 체크하기
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     assignment = list(map(int, input().split()))
    
#     print('#{}'.format(t), end=' ')
#     for i in range(1, n+1):
#         if i not in assignment:
#             print(i, end=' ')
#     print()

# ----------------------------------------------------------------------

# 1230. [S/W 문제해결 기본] 8일차 - 암호문3
'''
    문제 이해 및 접근방법      
        1. I(삽입) x, y, s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다.
                             s는 덧붙일 암호문들이다.[ ex) I 3 2 123152 487651 ]

        2. D(삭제) x, y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.
                          [ ex) D 4 4 ]

        3. A(추가) y, s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다.
                          s는 덧붙일 암호문들이다. [ ex) A 2 421257 796813 ]
    * 위와 같은 네 줄이 한 개의 테스트 케이스
'''
# from collections import deque

# tc = 10
# for t in range(1, tc+1):
#     n = int(input())  # 원본 암호문 뭉치 속 암호문의 개수
#     code = list(map(int, input().split()))  # 원본 암호문 뭉치
#     m = int(input())  # 명령어 개수
#     command = deque(input().split())  # 명령어
    
#     while command:
#         state = command.popleft()
#         if state == 'I':
#             x, y = int(command.popleft()), int(command.popleft())
#             s = [int(command.popleft()) for _ in range(y)]
#             for i in s[-1 : -len(s)-1 : -1]:
#                 code.insert(x+1, i)
#         elif state == 'D':
#             x, y = int(command.popleft()), int(command.popleft())
#             for _ in range(y):
#                 code.pop(x+1)
#         elif state == 'A':
#             y = int(command.popleft())
#             s = [int(command.popleft()) for _ in range(y)]
#             code += s
    
#     print('#{} {}'.format(t, ' '.join(map(str, code[1:11]))))
    
# ----------------------------------------------------------------------

# 3314. 보충학습과 평균
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     score = list(map(int, input().split()))
#     sum = 0
#     for i in score:
#         if i < 40:
#             sum += 40
#         else:
#             sum += i
#     print('#{} {}'.format(t, sum//len(score)))

# ----------------------------------------------------------------------

# 16910.원 안의 점
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     cnt = 0
#     for i in range(-n, n+1):
#         for j in range(-n, n+1):
#             if (i**2 + j**2) <= n**2:
#                 cnt += 1
#     print('#{} {}'.format(t, cnt))