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

tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    sale = list(map(int, input().split()))
    profit, lastDay = 0, sale[-1]   # 지난날 매매가
    for i in range(n-2, -1, -1):    # n-1번째날부터 계산 누적
        curDay = sale[i]            # 현재날        
        if lastDay > curDay:
            profit += (lastDay - curDay)
        else:
            lastDay = curDay        # 지난날을 현재날로 갱신
    print('#{} {}'.format(t, profit))

# ----------------------------------------------------------------------

# 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기



# ----------------------------------------------------------------------