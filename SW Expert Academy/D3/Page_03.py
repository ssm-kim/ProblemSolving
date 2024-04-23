import sys
sys.stdin = open('./public_input.txt', 'r', encoding='utf-8')

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
# 1213. [S/W 문제해결 기본] 3일차 - String
'''
     문제 이해 및 접근방법
'''
# tc = 10
# for t in range(1, tc+1):
#     t = int(input())
#     target = input()
#     s = input()
#     s = s.replace(target, '#')
#     print('#{} {}'.format(t, s.count('#')))

# ----------------------------------------------------------------------

# 1234. [S/W 문제해결 기본] 10일차 - 비밀번호
'''
     문제 이해 및 접근방법
        - 0~9로 이루어진 번호 문자열에서 같은 번호로 붙어있는 쌍들을 소거하고
          남은 번호를 비밀번호로 만드는 것
        - 번호 쌍이 소거되고 소거된 번호 쌍의 좌우 번호가 같은 번호이면 또 소거
'''
# tc = 10
# for t in range(1, tc+1):
#     n, num = input().split()
#     flag, num = True, list(num)
#     while flag:
#         for i in range(len(num)-1):
#             if num[i] == num[i+1]:
#                 num = num[:i] + num[i+2:]
#                 break
#         else:
#             flag = False
#     print('#{} {}'.format(t, ''.join(num)))
    
# ----------------------------------------------------------------------

# 2805. 농작물 수확하기
'''
     문제 이해 및 접근방법
         - n의 절반이상을 탐색하면 flag로 오른쪽 인덱스 값을 줄여준다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     grow = [list(map(int, input())) for _ in range(n)]
#     half, profit = n//2, 0
#     flag = 0
    
#     for i in range(n):
#         for j in range(abs(half - i), abs(half + i + 1 + flag)):
#             profit += grow[i][j]
#         if i >= half:
#             flag -= 2
#     print('#{} {}'.format(t, profit))
        
# ----------------------------------------------------------------------

# 3456. 직사각형 길이 찾기
'''
     문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     a, b, c = map(int,input().split())
#     if a == b:
#         answer = c
#     elif a == c:
#         answer = b
#     else:
#         answer = a
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 4751. 다솔이의 다이아몬드 장식
'''
     문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     answer = ['', '', '', '', '']
#     deco = [['.','.','#','.','.'],
#                ['.','#','.','#','.'],
#                ['#','.','.','.','#'],
#                ['.','#','.','#','.'],
#                ['.','.','#','.','.']]
#     adddeco = [['.','#','.','.'],
#                ['#','.','#','.'],
#                ['.','.','.','#'],
#                ['#','.','#','.'],
#                ['.','#','.','.']]
#     deco[2][2] = s.pop(0)

#     # 첫 시작
#     for i in range(len(deco)):
#         answer[i] += ''.join(deco[i])
    
#     # 추가 장식
#     for i in s:
#         for j in range(len(adddeco)):
#             tmp = ''
#             for k in range(len(adddeco[j])):
#                 if j == 2 and k == 1:
#                     tmp += i
#                     continue
#                 tmp += adddeco[j][k]
#             answer[j] += tmp
#     print('\n'.join(answer))

# ----------------------------------------------------------------------

# 5549.홀수일까 짝수일까 
'''
     문제 이해 및 접근방법
'''

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     if n%2 == 0:
#         answer = 'Even'
#     else:
#         answer = 'Odd'
#     print('#{} {}'.format(t, answer))


# ----------------------------------------------------------------------

# 1240. [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
'''
     문제 이해 및 접근방법
          - 암호코드는 8개의 숫자로 이루어져 있다.
          - 올바른 암호코드는 (홀수 자리의 합 x 3) + (짝수 자리의 합)이 10의 배수가 되어야 한다.
          - 길이가 56가 아닌 코드는 주어지지 않는다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     psCode = {'0001101':0, '0011001':1, '0010011':2, '0111101':3, '0100011':4,
#               '0110001':5, '0101111':6, '0111011':7, '0110111':8, '0001011':9}
#     board = [list(input()) for _ in range(n)]
#     answer = 0
#     for rows in board:
#         tmp = ''
#         if '1' in rows:
#             lt = ''.join(rows).index('1')
#             for i in range(lt, len(rows)):
#                 if '1' not in rows[i:]:
#                     break
#                 tmp += rows[i]
#             checkLen = 56-len(tmp)   # 56길이보다 작으면
#             if checkLen > 0: 
#                 tmp = ('0' * checkLen) + tmp    

#             bit = list()
#             for i in range(0, len(tmp), 7):  # 7자로 분할
#                 bit.append(psCode[tmp[i:i+7]])
            
#             # 검증
#             even, odd = 0, 0
#             for i in range(len(bit)):
#                 if (i%2) == 0:
#                     even += bit[i]
#                 else:
#                     odd += bit[i]
#             total = even*3 + odd
    
#             if total%10 == 0:
#                 answer = sum(bit)
#     print('#{} {}'.format(t, answer))
     
    
# ----------------------------------------------------------------------

# 10804. 문자열의 거울상
'''
     문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     s = input()
#     answer = ''
#     for i in s:
#         if i == 'b':
#             answer = 'd' + answer
#         elif i == 'd':
#             answer = 'b' + answer
#         elif i == 'p':
#             answer = 'q' + answer
#         else:
#             answer = 'p' + answer
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1216. [S/W 문제해결 기본] 3일차 - 회문2  >  다시 풀어보기
'''
    문제 이해 및 접근방법
        - "기러기" 또는 "level" 과 같이 거꾸로 읽어도 제대로 읽은 것과 같은 문장이나 낱말을 회문(palindrome)
        - 주어진 100x100 평면 글자판에서 가로, 세로를 모두 보아 가장 긴 회문의 길이를 구해라
'''

def check(lst):
    st = ''.join(lst)
    for i in range(len(st)//2):
        if st[i] != st[-1-i]:
            return False
    return True

tc = 10
for t in range(1, tc+1):
    t = int(input())
    row = [list(input()) for _ in range(100)]
    col = list()
    for i in range(100):
        s = ''
        for j in range(100):
            s += row[j][i]
        col.append(s)

    answer = 0
    for i in range(100):
        for j in range(1, 100+1):
            for k in range(100):
                if check(row[k][i:j]) or check(col[k][i:j]):
                    answer = max(answer, len(row[k][i:j]), len(col[k][i:j]))
    print('#{} {}'.format(t, answer))

# def check(s):  # 회문판별함수 완료
#     half = len(s)//2
#     if (len(s)%2) == 0:
#         return s[:half] == ''.join(reversed(s[half:]))
#     else:
#         return s[:half] == ''.join(reversed(s[half+1:]))

# tc = 10
# for t in range(1, tc+1):
#     t = int(input())
#     row = [list(input()) for _ in range(100)]
#     col = list()
#     for i in range(100):
#         s = ''
#         for j in range(100):
#             s += row[j][i]
#         col.append(s)

#     # 0행: 0 ~ 99, 1 ~ 99, 2 ~ 99, 3 ~ 99 ... 
#     # 1행: 0 ~ 99, 1 ~ 99, 2 ~ 99, 3 ~ 99 ... 
#     # 99행까지 반복
#     answer = 0
#     for i in range(len(row)):  
#         for j in range(len(row)+1):
#             for k in range(len(row)):
#                 if check(''.join(row[k][i:j])) or check(''.join(col[k][i:j])):
#                     answer = max(answer, len(row[k][i:j]), len(row[k][i:j]))
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 14692. 통나무 자르기
'''
    문제 이해 및 접근방법
        1. 게임은 Alice가 먼저 시작하며 그 이후 둘이 번갈아가면서 턴을 가진다.
        2. 각 턴을 맡은 사람은 통나무를 두 조각으로 나누는데,
           이 때 잘린 통나무가 모두 자연수(1 이상의 정수) 미터 길이를 가지도록 잘라야 한다.
        3. 더 이상 자를 수 없게 되는 사람이 진다. 누가 이기는가?
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     if n%2 == 0:
#         answer = 'Alice'
#     else:
#         answer = 'Bob'
#     print('#{} {}'.format(t, answer))