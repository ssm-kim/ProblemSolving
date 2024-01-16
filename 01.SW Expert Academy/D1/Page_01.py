import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D1
      - 참여자순 
      - 1페이지 단위 ( 10문제 )
'''

# 2072. 홀수만 더하기
# tc = int(input())

# for t in range(tc):
#   num_list = list(map(int, input().split()))
#   answer = 0

#   for i in num_list:
#     if i%2 == 1:
#       answer += i

#   print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------
  
# 2071. 평균값 구하기
tc = int(input())

for t in range(1, tc+1):
    num_list = list(map(int, input().split()))
    answer = round(sum(num_list)/10, 0)
    
    print('#{} {:.0f}'.format(t, answer))