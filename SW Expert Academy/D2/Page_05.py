import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 정답율순
      - 1페이지 단위 ( 10문제 )
'''
# 1940. 가랏! RC카!
#   - 현재 속도보다 감속할 속도가 더 클 경우, 속도는 0 m/s 가 된다.

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     cur_speed = 0     # 초기속도는 0이다.
#     distance = 0  # 총 이동거리
#     for _ in range(n):
#         command = list(map(int, input().split()))
#         if len(command) == 1:  # 현재속도유지
#             distance += cur_speed
#         else:                  # 입력값이 2개일 때 속도증감시키기
#             state, add_speed = command

#             if state == 1:    
#                 cur_speed += add_speed  # 가속
#             elif state == 2:
#                 if add_speed > cur_speed:  # 현재속도가 감속속도보다 크면 0으로 초기화
#                     cur_speed = 0
#                 else:
#                     cur_speed -= add_speed  # 감속

#             distance += cur_speed   # 현재속도만큼 거리 증가
#     print('#{} {}'.format(t,  distance))

# ----------------------------------------------------------------------

# 1979. 어디에 단어가 들어갈 수 있을까
# tc = int(input())
# for t in range(1, tc+1):
#     n, k = map(int, input().split())
#     board = [list(map(int, input().split())) for _ in range(n)]
#     answer, ch_row, ch_col = 0, 0, 0
#     for i in range(n):
#         for j in range(n):
#             if board[i][j] == 1:  # 가로체크
#                 ch_row += 1
#             else:
#                 ch_row = 0
#             if ch_row == k:
#                 if (j == n-1) or (j+1 < n and board[i][j+1] == 0):  # j가 n-1에 도달하거나 다음인덱스 값이 0이면 증가
#                     answer += 1

#             if board[j][i] == 1:  # 세로체크
#                 ch_col += 1
#             else:
#                 ch_col = 0
#             if ch_col == k:
#                 if (j == n-1) or (j+1 < n and board[j+1][i] == 0):  # j가 n-1에 도달하거나 다음인덱스 값이 0이면 증가
#                     answer += 1
#         ch_row, ch_col = 0, 0  # 1행, 1열 체크했으면 초기화
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1961. 숫자 배열 회전
#   - 시계 방향으로 90도, 180도, 270도 회전한 모양 출력

# def rotate(board):
#     tmp = list()
#     for i in range(n):
#         s = ''
#         for j in range(n-1, -1, -1):
#             s += board[j][i]
#         answer[i].append(s)
#         tmp.append(list(s))
#     return tmp

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     board = [list(input().split()) for _ in range(n)]
#     answer = [list() for _ in range(n)]

#     rotate90 = rotate(board)
#     rotate180 = rotate(rotate90)
#     rotate270 = rotate(rotate180)
    
#     print('#{}'.format(t))
#     for i in answer:
#         print(' '.join(i))

# ----------------------------------------------------------------------

# 1954. 달팽이 숫자
# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     board = [[0]*n for _ in range(n)]
#     dx = [0, 1, 0, -1]
#     dy = [1, 0, -1, 0]
#     cur_x, cur_y, dir = 0, 0, 0
#     for i in range(1, n*n+1):
#         board[cur_x][cur_y] = str(i)
#         cur_x += dx[dir]  # 인덱스 이동
#         cur_y += dy[dir]
#         if cur_x < 0 or cur_x >= n or cur_y < 0 or cur_y >= n or board[cur_x][cur_y] != 0:
            
#             cur_x -= dx[dir]  # 복구
#             cur_y -= dy[dir]
            
#             dir = (dir+1) % 4
            
#             cur_x += dx[dir]  # 방향변경 후 인덱스이동
#             cur_y += dy[dir]
#     print('#%d' % t)
#     for i in board:
#         print(' '.join(i))

# ----------------------------------------------------------------------

# 2001. 파리 퇴치
#   - M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     board = [list(map(int, input().split())) for _ in range(n)]
#     answer = 0

#     for i in range(n-m+1):
#         for j in range(n-m+1):
#             cnt = 0
#             for k in range(i, i+m):
#                 for l in range(j, j+m):
#                     cnt += board[k][l]
#             answer = max(answer, cnt)
#     print('#{} {}'.format(t, answer))
    
# ----------------------------------------------------------------------

# 1974. 스도쿠 검증

# def check(tmp):
#     if len(set(tmp)) == 9:
#         return True
#     return False

# tc = int(input())
# for t in range(1, tc+1):
#     sudoku = [list(map(int, input().split())) for _ in range(9)]
#     answer = 1
#     x, y = 0, 0  # 3*3 체크
#     for i in range(9):
#         row, col = sudoku[i], list()  # 가로, 세로 체크
#         for j in range(9):    
#             col.append(sudoku[j][i])

#         if y == 9:
#             y = 0
#             x += 3
#         box = list()
#         for j in range(x, x+3):  # 3*3체크
#             for k in range(y, y+3):
#                 box.append(sudoku[j][k])
#         y += 3

#         if not check(row) or not check(col) or not check(box):  # 길이가 9보다 작을 때
#             answer = 0
#             break

#     print('#{} {}'.format(t, answer))

# ---------------------------------------------------------------------- 

# 2005. 파스칼의 삼각형

tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    
    break

# ----------------------------------------------------------------------