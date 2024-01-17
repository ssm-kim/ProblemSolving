import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D1
      - 참여자순 
      - 1페이지 단위 ( 10문제 )
    
    구분선을 기준으로 1문제씩 실행.
    
'''

# 2072. 홀수만 더하기
tc = int(input())

for t in range(tc):
  num_list = list(map(int, input().split()))
  answer = 0

  for i in num_list:
    if i%2 == 1:
      answer += i

  print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2071. 평균값 구하기
tc = int(input())
  
for t in range(1, tc + 1):
    num_list = list(map(int, input().split()))
    answer = sum(num_list)/10
    
    print('#{} {:.0f}'.format(t, answer))

# ----------------------------------------------------------------------

# 2070. 큰 놈, 작은 놈, 같은 놈
tc = int(input())

for t in range(1, tc+1):
    num1, num2 = map(int, input().split())
    
    if num1 > num2:
        answer = '>'
    elif num1 < num2:
        answer = '<'
    else:
        answer = '='

    print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 2063. 중간값 찾기


# ----------------------------------------------------------------------

# 2058. 자릿수 더하기


# ----------------------------------------------------------------------