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
        - 둘은 15번 팔씨름을 하여 8번 이상 이기는 사람이 점심 값을 면제받기로 하였다.
        - S[i]가 ‘o’면 소정이가 i번째 경기에서 승리했다는 것이고, ‘x’면 패배했다는 것이다.
    
    접근방법
        - 문제의도는 면제받을가능성 유무를 파악하는 것
        - 순서가 중요하지 않으므로 총 15글자에서 절반이상이 'o'이거나 절반미만이 'x'라면
          면제받을가능성이 존재한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     answer = 'NO'
#     if s.count('o') >= 8 or s.count('x') <= 7:
#         answer = 'YES'
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------
    
# 11736. 평범한 숫자
'''
    문제 이해 및 접근방법
        - 1 이상 N 이하의 정수가 적혀 있는 길이 N의 순열 p1, p2, …, pN이 있다.
        - 수열에 있는 모든 숫자는 서로 다르다.
        - (핵심) 2 ≤ i ≤ N-1이며
                 pi-1, pi, pi+1 중 pi가 최솟값도, 최댓값도 아니라면 pi를 평범한 숫자라고 정의한다.
        -  전체 배열에서의 최솟값과 최댓값이 아니라 양옆 숫자랑 비교하는 것.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     p = list(map(int, input().split()))
#     basicNum = 0
#     for i in range(1, n-1):
#         low, high = min(p[i-1], p[i], p[i+1]), max(p[i-1], p[i], p[i+1])
#         if low < p[i] < high:
#             basicNum += 1
#     print('#{} {}'.format(t, basicNum))

# ----------------------------------------------------------------------
    
# 1221. [S/W 문제해결 기본] 5일차 - GNS
'''
    문제 이해 및 접근방법
        - 0 ~ 9 의 값을 나타내는 단어가 섞여 있는 문자열을 받아 작은 수부터 차례로 정렬하여 출력
    접근방법
        1. 딕셔너리 생성 ("ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN")
        2. 문자열 입력받을 시 미리생성한 딕셔너리를 활용하여 숫자로 변환하여 리스트에 담는다.
        3. 숫자로 구성된 리스트 정렬
        4. 미리 생성된 딕셔너리의 키, 값을 서로 변경
        5. 정렬된 리스트를 변경된 딕셔너리를 활용하여 값 출력
'''
# tc = int(input())
# for _ in range(1, tc+1):
#     t, n = input().split()
#     n = int(n)
#     planet = { "ZRO":0, "ONE":1, "TWO":2, "THR":3, "FOR":4,
#                "FIV":5, "SIX":6, "SVN":7, "EGT":8, "NIN":9 }
#     nums = [planet[i] for i in list(input().split())]  # 입력받은 리스트를 바로 숫자로 변환
#     nums.sort()  # 정렬

#     planet = { v:k for k, v in planet.items() }  # planet 딕셔너리의 key, value값 변경
#     words = [planet[i] for i in nums]
#     print('{}\n{}'.format(t, ' '.join(words)))

# ----------------------------------------------------------------------

# 1206. [S/W 문제해결 기본] 1일차 - View
'''
    문제 이해 및 접근방법
        - 왼쪽과 오른쪽으로 창문을 열었을 때, 양쪽 모두 거리 2 이상의 공간이 확보될 때 조망권이 확보
        - 조망권이 확보된 세대의 수를 반환하여라.
        - 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에 있는 건물은 항상 높이가 0이다.
'''
# tc = 10
# for t in range(1, tc+1):
#     n = int(input())
#     height = list(map(int, input().split()))
#     secureCnt = 0
#     for i in range(2, n-2):
#         buildings = height[i-2:i+3]
#         mid = buildings.pop(2)
#         if mid > max(buildings):
#             secureCnt += (mid - max(buildings))
#     print('#{} {}'.format(t, secureCnt))

# ----------------------------------------------------------------------

# 1229. [S/W 문제해결 기본] 8일차 - 암호문2  >  암호문3번 문제를 풀었으므로 Pass