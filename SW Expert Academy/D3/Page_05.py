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

# 11856. 반반
'''
    문제 이해 및 접근방법
        - S에 정확히 두 개의 서로 다른 문자가 등장하고, 각 문자가 정확히 두 번 등장
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     check = {}
#     answer = 'Yes'
#     for i in s:
#         if i not in check:
#             check[i] = 1
#         else:
#             check[i] += 1
#     if len(check) != 2:
#         answer = 'No'
#     print('#{} {}'.format(t, answer))
    
# ----------------------------------------------------------------------

# 1209. [S/W 문제해결 기본] 2일차 - Sum
'''
    문제 이해 및 접근방법
        - 단순 구현
'''
# tc = 10
# for t in range(1, tc+1):
#     s = list(input())
#     board = [list(map(int, input().split())) for _ in range(100)]
#     row, col, lt, rt = 0, 0, 0, 0  # 왼쪽/오른쪽 대각선합
#     for i in range(len(board)):
#         row = max(sum(board[i]), row)  # 행 최댓값 연산
#         colSum = 0
#         for j in range(len(board)):
#             colSum += board[j][i]
#             if i == j:
#                 lt += board[i][j]
#                 rt += board[i][99-j]
#         col = max(colSum, col)  # 열 최댓값 연산
#     print('#{} {}'.format(t, max(row, col, lt, rt)))

# ----------------------------------------------------------------------

# 3499. 퍼펙트 셔플
'''
    문제 이해 및 접근방법
        - 카드를 퍼펙트 셔플 한다는 것은
          카드 덱을 정확히 절반으로 나누고 나눈 것들에서 교대로 카드를 뽑아 새로운 덱을 만드는 것을 의미한다. 
        - 만약 N이 홀수이면, 교대로 놓을 때 먼저 놓는 쪽에 한 장이 더 들어가게 하면 된다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     cardDq = list(input().split())
#     answer = ''

#     for i in range(len(cardDq)//2):
#         if len(cardDq)%2 == 0:  # 짝수일때
#             answer += cardDq[i] + ' '
#             answer += cardDq[len(cardDq)//2+i] + ' '
#         else:  # 홀수일때
#             answer += cardDq[i] + ' '
#             answer += cardDq[len(cardDq)//2+i+1] + ' '
    
#     if len(cardDq)%2 != 0:  # 홀수라면 중간값 추가
#         answer += cardDq[len(cardDq)//2]

#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 5515. 2016년 요일 맞추기
'''
    문제 이해 및 접근방법
        - 2016년 1월 1일은 금요일
        - 2016년은 윤년이기 때문에 2월 29일이 포함된다. 2016년 m월 d일은 무슨 요일인지 맞추는 프로그램
        - 월요일이면 0, 화요일이면 1, 수요일이면 2, 목요일이면 3,
          금요일이면 4, 토요일이면 5, 일요일이면 6을 출력
'''
# tc = int(input())
# for t in range(1, tc+1):
#     m, d = map(int, input().split())
#     calendar = { 1:31, 2:29, 3:31,  4:30,  5:31, 6:30,
#                  7:31, 8:31, 9:30, 10:31, 11:30, 12:31 }
#     weekend = { 0: 3, 1: 4, 2: 5, 3: 6, 4: 0, 5: 1, 6: 2 } # 시작일자 1월1일 :요일
#     days = 0
#     if m == 1:  # 1월달은 d값을 대입
#         days = d
#     else:
#         for i in range(m-1):  # 2월부터는 월별일수를 더한다. 단, 마지막월은 d값을 넣는다.
#             days += calendar[i+1]
#         days += d
    
#     print('#{} {}'.format(t, weekend[days % 7]))

# ----------------------------------------------------------------------

# 5356. 의석이의 세로로 말해요
'''
    문제 이해 및 접근방법
        - try ~ except 예외처리구문 활용
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = [list(input()) for _ in range(5)]
#     answer = ''
#     length = 0
#     for i in s:
#         length = max(len(i), length)

#     for i in range(length):
#         for j in range(5):
#             try:
#                 answer += s[j][i]
#             except:
#                 continue
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 13547. 팔씨름
'''
    문제 이해 및 접근방법
'''



# ----------------------------------------------------------------------