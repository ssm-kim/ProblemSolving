import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D2
      - 참여자순 
      - 1페이지 단위 ( 10문제 )
'''

# 1859. 백만 장자 프로젝트

# 접근방법
# 1. 매매가 리스트를 역순으로 비교한다.
# 2. n이 n-1보다 크다면 n-1째 물건을 구매 후 판매한다. (하루 최대 1만큼 구입가능)
# 3. n이 n-1보다 작다면 매매가만 갱신하고 아무것도 구매하지 않는다. (Pass)
# 계산식 - total += (n - (n-1))

# tc = int(input())
# for t in range(1, tc+1):
#     n = int(input())
#     sale = list(map(int, input().split()))
#     profit, lastDay = 0, sale[-1]   # 지난날 매매가
#     for i in range(n-2, -1, -1):    # n-1번째날부터 계산 누적
#         curDay = sale[i]            # 현재날        
#         if lastDay > curDay:
#             profit += (lastDay - curDay)
#         else:
#             lastDay = curDay        # 지난날을 현재날로 갱신
#     print('#{} {}'.format(t, profit))

# ----------------------------------------------------------------------

# 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기
# 접근방법
# 1. 딕셔너리 선언 후 value값을 1씩 더한다.
# 2. value값으로 역순 정렬
# 3. 만약 최빈수 key가 여러개라면 max로 가장 큰 값 출력

# tc = int(input())
# for _ in range(1, tc+1):
#     t = int(input())
#     num_list = list(map(int, input().split()))
#     checkCnt = dict()
#     answer = 0

#     for i in range(len(num_list)):
#         if num_list[i] not in checkCnt:
#             checkCnt[num_list[i]] = 1
#         else:
#             checkCnt[num_list[i]] += 1

#     mostCnt = sorted(checkCnt.values(), reverse=True)[0]  # 최빈수 추출
    
#     for k, v in checkCnt.items():        
#         if v == mostCnt:
#             if k > answer:  # 최빈수가 같다면 더 큰 값으로 갱신
#                 answer = k
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1954. 달팽이 숫자  >  재시도
# 접근방법
# 1. 동서남북 방향벡터선언 dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}
# 2. 0으로 초기화된 n*n 이중리스트 선언
# 3. (1, n*n+1)만큼 반복문 실행
# 4. nx, ny를 0으로 초기화하고 반복문을 통해서 값을 대입해준다.
# 5. 만약 0 ~ n의 범위를 벗어나거나 answer[nx][ny]값이 0이 아니라면 nx, ny를 변경한다.
#    그 이유는 현재값이 채워져있으므로 다음방향으로 돌려야하기 때문
# tc = int(input())
# dx = [0, 1, 0, -1]  # 고정 x, y 좌표
# dy = [1, 0, -1, 0]
# for t in range(1, tc+1):
#     n = int(input())
#     answer = [[0] * n for _ in range(n)]
#     nx, ny = 0, 0   # 현재 x, y 좌표
#     dir = 0         # 좌표의 방향 인덱스 값
#     for i in range(1, n*n + 1):
#         answer[nx][ny] = i
#         nx += dx[dir]
#         ny += dy[dir]
#         # 범위를 벗어나거나 값이 채워져 있다면 방향전환
#         if not (0 <= nx < n) or not (0 <= ny < n) or answer[nx][ny] != 0:
#             nx -= dx[dir]   # 복귀
#             ny -= dy[dir]

#             dir = (dir+1) % 4

#             nx += dx[dir]   # 변경된 dir 변수이므로 다음 인덱스로 이동
#             ny += dy[dir]
    
#     print('#{}'.format(t))
#     for x in answer:
#         print(' '.join(map(str, x)))

# ----------------------------------------------------------------------

# 1926. 간단한 369게임
# 접근방법
# 1. 입력값을 str타입으로 변경 후 리스트에 저장
# 2. 현재 값을 check 변수에 대입
# 3. check 안에 3, 6, 9 하나라도 있다면 '-'로 변경

# n = int(input())
# num_list = [str(i) for i in range(1, n+1)]
# for i in num_list:
#     check = i
#     for j in i:
#         if j in ['3', '6', '9']:
#             if check.isdecimal():
#                 check = '-'
#             else:
#                 check += '-'
#     print(check, end=' ')

# ----------------------------------------------------------------------

# 2001.파리 퇴치
# 접근방법
# 1. n*n 이중리스트에 입력값을 초기화
# 2. 범위에 벗어나지 않도록 n-m+1만큼 반복문을 순회
# 3. n-m+1 반복문 안에 (i, m+i), (j, m+j) 만큼 순회하면서 최댓값 갱신
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     bug = [list(map(int, input().split())) for _ in range(n)]
#     answer = 0

#     for i in range(n-m+1):
#         for j in range(n-m+1):
#             dieCnt = 0
#             for k in range(i, m+i):
#                 for l in range(j, m+j):
#                     dieCnt += bug[k][l]
#             if dieCnt > answer:
#                 answer = dieCnt
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1974. 스도쿠 검증
# 접근방법
# 총 9*9 가로, 열 중복 검사
# 3*3 검사
# tc = int(input())
# for t in range(1, tc+1):
# 	sudoku = [list(map(int, input().split())) for _ in range(9)]
# 	answer = 1
# 	for i in range(9):
# 		row = set(sudoku[i])  # 가로 중복 검사
# 		col = set()			  # 세로 중복 검사
# 		for j in range(9):
# 			col.add(sudoku[j][i])
			
# 		if len(row) != 9 or len(col) != 9:
# 			answer = 0

# 		# 3*3 중복 검사
# 		if i in [0, 3, 6]:
# 			n = 0
# 			for _ in range(3):
# 				blockThree = set()
# 				for k in range(i, i+3):
# 					for l in range(n, n+3):
# 						blockThree.add(sudoku[k][l])
				
# 				if len(blockThree) != 9:
# 					answer = 0
# 					break
# 				n += 3

# 		if answer == 0:
# 			answer = 0
# 			break
# 	print('#{} {}'.format(t, answer))
	
# ----------------------------------------------------------------------

# 2007. 패턴 마디의 길이
# 접근방법
# 1. 핵심 로직  >  s[:i] == s[i:i*2]
# 2. (s[:1], s[1:2]) (s[:2], s[2:4]) ...
# tc = int(input())
# for t in range(1, tc+1):
# 	s = input()
# 	pattern = ''
# 	for i in range(1, len(s)):
# 		if s[:i] == s[i:i*2]:
# 			pattern = ''.join(s[:i])
# 			break
	
# 	print('#{} {}'.format(t, len(pattern)))	

# ----------------------------------------------------------------------

# 2005. 파스칼의 삼각형  >  재시도
# 접근방법

# tk = int(input())
# for t in range(1, tk+1):
# 	n = int(input())
# 	arr = [[0]*n for _  in range(n)]
# 	for i in range(n):
# 		for j in range(i+1): 
# 			if j == 0 or j == i:
# 				arr[i][j] = 1
# 			else:
# 				arr[i][j] += arr[i-1][j-1] + arr[i-1][j]
# 	print(arr)
# ----------------------------------------------------------------------

# 1284. 수도 요금 경쟁
# tc = int(input())
# for t in range(1, tc+1):
# 	P, Q, R, S, W = map(int, input().split())
	
# 	a = P * W  # A사 요금

# 	if W <= R:
# 		b = Q
# 	else:
# 		b = Q + S * (W - R) 
	
# 	print('#{} {}'.format(t, min(a, b)))

# ----------------------------------------------------------------------

# 1979. 어디에 단어가 들어갈 수 있을까  >  재시도
# 접근방법
# 1. n*n 행렬에 가로와 세로에 k길이만큼만 있는지 확인
# 2. range(n-2)로 2중 for문
# 2. 맞다면 cnt + 1

# tc = int(input())
# for t in range(1, tc+1):
# 	n, k = map(int, input().split())
# 	puzzle = [list(map(int, input().split())) for _  in range(n)]
# 	answer = 0
# 	for i in range(n):
# 		row, col = 0, 0
# 		for j in range(n):
# 			# 가로 체크
# 			if puzzle[i][j] == 0:
# 				if row == k:
# 					answer += 1
# 				row = 0
# 			else:
# 				row += 1
			
# 			# 세로 체크
# 			if puzzle[j][i] == 0:
# 				if col == k:
# 					answer += 1
# 				col = 0
# 			else:
# 				col += 1
# 		# 한번 더 체크
# 		if row == k:
# 			answer += 1
# 		if col == k:
# 			answer += 1

# 	print('#{} {}'.format(t, answer))