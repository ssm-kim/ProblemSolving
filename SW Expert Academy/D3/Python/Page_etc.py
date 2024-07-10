import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D3 정렬조건
        - 정답율순, Python
        - 1페이지 단위 ( 10문제 )
    
    ※ 8페이지 중반부터는 제출 많은 수 문제 외주로 풀이
'''

# 5215. 햄버거 다이어트  (9페이지)
'''
    문제 이해 및 접근방법
        - 준비해놓은 재료를 그대로 사용하여 고객이 원하는 조합으로 햄버거를 만들어서 준다.
        - 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해보자.
'''

# def dfs(v, score, cur_calories):
#     if cur_calories > k:    # 현재 칼로리가 제한 칼로리보다 크다면
#         return
#     total.append(score)
#     if n == v:  # 정점이 n과 같다면 재귀중지
#         return
    
#     dfs(v+1, score+taste[v][0], cur_calories+taste[v][1])
#     dfs(v+1, score, cur_calories)

# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     taste = [list(map(int, input().split())) for _ in range(n)]
#     total = list()
#     dfs(0, 0, 0)
#     print('#{} {}'.format(t, max(total)))

# ----------------------------------------------------------------------

# 14361. 숫자가 같은 배수 (9페이지)
'''
    문제 이해 및 접근방법
        - itertools 사용안하고 DFS활용하여 순열로 풀이해보기
'''
# from itertools import permutations
# tc = int(input())
# for t in range(1, tc+1):
#     n = list(input())
#     answer = 'impossible'

#     for i in permutations(n, len(n)):
#         div = int(''.join(i))
#         if div <= int(''.join(n)):
#             continue
#         if div % int(''.join(n)) == 0:
#             answer = 'possible'
#             break
    
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1860. 진기의 최고급 붕어빵 (9페이지)
'''
    문제 이해 및 접근방법
        - 진기는 0초부터 붕어빵을 만들기 시작하며, M초의 시간을 들이면 K개의 붕어빵을 만들 수 있다.
        - 붕어빵이 완성되면 어떤 시간 지연도 없이 다음 붕어빵 만들기를 시작할 수 있다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, m, k = map(int, input().split())
#     arrive_time = list(map(int, input().split()))  # 손님 도착하는 시간
#     arrive_time.sort()
#     cur, guest, complete = 0, 0, 0  # 현재 시간, 손님별 도착시간, 완성된 붕어빵 갯수
#     last_time = arrive_time[-1]  # 마지막 손님 도착시간
#     answer = 'Possible'
    
#     while cur <= last_time:  # 0초부터 붕어빵을 만들어서 0초부터 확인해야함.
#         if arrive_time and guest == 0:
#             guest = arrive_time.pop(0)
#         # print(cur, complete, guest)
#         if cur == guest:      # 현재시간과 손님도착시간이 같을 때 
#             if complete > 0:  # 만들어진 붕어빵이 있다면
#                 complete -= 1
#                 guest = 0
#             else:
#                 answer = 'Impossible'
#                 break
#         cur += 1
#         if (cur % m) == 0:
#             complete += k
    
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 6485. 삼성시의 버스 노선
'''
    문제 이해 및 접근방법
        - 5,000개의 버스 정류장은 관리의 편의를 위해 1에서 5,000까지 번호가 붙어 있다.
        - 버스 노선은 N개가 있다.
    
    ※ p값이 1 ~ 5,000 사이의 값이 들어올 수 있으므로 5,000개의 버스정류장을 정의해야한다. 
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     bus_route = [list(map(int, input().split())) for _ in range(n)]  # 버스 노선 (A, B)
#     p = int(input())
#     bus_stop = {i:0 for i in range(1, 5001)}  # 1 ~ 5000번 버스정류장
    
#     for start, stop in bus_route:
#         for i in range(start, stop+1):
#             bus_stop[i] += 1

#     p_stop = [int(input()) for _ in range(p)]  # p번 버스정류장

#     print('#{} {}'.format(t, ' '.join([str(bus_stop[i]) for i in p_stop])))

# ----------------------------------------------------------------------

# 11315. 오목 판정 (10 페이지)
'''
    문제 이해 및 접근방법
        - N * N 크기의 판이 있다.
        - 돌이 가로, 세로, 대각선 중 하나의 방향으로 다섯 개 이상 연속한 부분이 있는지 없는지 판정해라
'''
# def search(cur_x, cur_y):  # 탐색할 현재 (x, y, 돌개수) 인덱스 넘버
    
#     for i in range(4):   # 4방향 탐색
#         dir, cnt = i, 1  # dx, dy 방향인덱스, 오목인지 아닌지 판별 cnt 변수
#         next_x = cur_x + dx[dir]
#         next_y = cur_y + dy[dir]
#         # 오목칸 범위안에 있고 다음 값이 'o'이면 탐색 유지
#         while (0 <= next_x < n) and (0 <= next_y < n) and board[next_x][next_y] == 'o':
#             cnt += 1
#             next_x += dx[dir]  # while 1번이라도 성공하면 방향은 정해짐
#             next_y += dy[dir]
#             if cnt >= 5:       # 하나의 방향으로 5개 이상 연속한 부분이 있다면 True
#                 return True
#     return False

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     board = [list(input()) for _ in range(n)]
#     answer = 'NO'
#     dx = [1, 1, 1, 0]  # 방향은 왼쪽대각선, 아래, 오른쪽대각선, 오른쪽 총 4가지
#     dy = [-1, 0, 1, 1]
#     for i in range(n):
#         for j in range(n):
#             if board[i][j] == 'o':   # 'o'(돌)이 있는 칸일때
#                 if search(i, j):     
#                     answer = 'YES'
#                     break
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
'''
    문제 이해 및 접근방법
        - 
'''
# def dfs(v, curr):
#     if v == len(board):  # 현재정점이 숫자판 길이와 같다면
#         combi_list.append(curr[:])
#         return
    
#     for next_v in range(v, len(board)):
#         if board[next_v] not in combi_list:
#             curr.append(board[next_v])
#             dfs(v+1, curr)
#             curr.pop()
#     return

# tc = int(input())
# for t in range(1, tc+1):
#     board, exchange = input().split()  # 숫자판 정보, 교환횟수
#     exchange = int(exchange)
#     combi_list = list()
#     print(board, exchange)
#     dfs(0, [])
#     print(combi_list)
#     if t == 2:
#         break

# ----------------------------------------------------------------------

# 5607. [Professional] 조합  >  페르마소의 정리풀이 Pass

'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, r = map(int, input().split())

# ----------------------------------------------------------------------

# 13428. 숫자 조작
'''
    문제 이해 및 접근방법
        - 9자리 이하의 음이 아닌 정수 N
        - 한 쌍의 숫자를 골라 그 위치를 바꾸는 일을 최대 한 번 하여(안 하거나, 한 번만 하여) 새로운 수 M을 만들 수 있다.
    * 단, 바꾼 결과 M의 맨 앞에 ‘0’이 나타나면 안 된다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = list(input())
#     check = list([int(''.join(n))])
    
#     for i in range(len(n)):
#         for j in range(i+1, len(n)):
#             n[i], n[j] = n[j], n[i]  # 변경
#             num = int(''.join(n))
#             if n[0] == '0':  # 맨 앞 '0'이면 Pass
#                 n[i], n[j] = n[j], n[i]  # 복구
#                 continue

#             if num not in check:
#                 check.append(num)
#             n[i], n[j] = n[j], n[i]  # 복구
    
#     print('#{} {} {}'.format(t, min(check), max(check)))

# ----------------------------------------------------------------------

# 7675. 통역사 성경이  >  다시 풀어보기
'''
    문제 이해 및 접근방법
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     sentence = ''
#     while True:
#         sentence += input()  # 하나의 문장으로 통합
#         stopPoint = sentence.count('.') + sentence.count('?') + sentence.count('!')  # 구두점 갯수
#         if stopPoint == n:   # 문장갯수
#             break
    
#     sentence = sentence.split()
#     cnt = 0
#     answer = list()

#     for i in range(len(sentence)):
#         word = sentence[i].rstrip('.!?')
    
#         if word.isalpha():
#             if word[0].isupper() and word[1:].islower():
#                 cnt += 1
#             if len(word) == 1 and word[0].isupper():
#                 cnt += 1

#  
#        if sentence[i][-1] in ['.', '!', '?']:
#             answer.append(str(cnt))
#             cnt = 0

#     print('#{} {}'.format(t, ' '.join(answer)))

# ----------------------------------------------------------------------

# 13732. 정사각형 판정  >  다시풀어보기
'''
    문제 이해 및 접근방법
        - N*N 크기의 격자판이 있다.
        - 각각의 격자는 비어 있거나(‘.’), 막혀 있다(‘#’).
        - 막혀 있는 칸들이 하나의 정사각형을 이루는지를 판단하는
'''
tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    board = [list(input()) for _ in range(n)]
    coordinate = list()  # (x, y) '#' 좌표
    for i in range(n):
        for j in range(n):
            if board[i][j] == '#':
                coordinate.append((i, j))
    
    leftTopx, leftTopy = coordinate[0]       # 좌상단 좌표
    rightDownx, rightDowny = coordinate[-1]  # 우하단 좌표

    curr_cnt = ( max(rightDownx, rightDowny)+1 - max(leftTopx, leftTopy) )**2  # 구해야할 정사각형 넓이
    square_cnt = 0       # 총 '#' 갯수

    for i in range(leftTopx, rightDownx+1):      # x부터 y+1까지 행 확인
        for j in range(leftTopy, rightDowny+1):  # x부터 y+1까지 열 확인
            if board[i][j] == '#':
                square_cnt += 1
        
    answer = 'no'
    if square_cnt == curr_cnt:
        answer = 'yes'
    
    print('#{} {}'.format(t, answer))