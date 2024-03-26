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
'''
tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    break