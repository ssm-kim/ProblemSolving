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

