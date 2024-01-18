import sys
sys.stdin = open('./public_input.txt', 'r')

'''
    SWEA D1
      - 참여자순 
      - 1페이지 단위 ( 10문제 )
    
    구분선을 기준으로 1문제씩 실행.
    
'''

# 1936. 1대1 가위바위보
# 가위는 1, 바위는 2, 보는 3으로 표현

a, b = map(int, input().split())
answer = 'B'
if (a == 2 and b == 1) or (a == 1 and b == 3) or (a == 3 and b == 2):
    answer = 'A'
print(answer)

# ----------------------------------------------------------------------

# 2050. 알파벳을 숫자로 변환
n = input()
answer = ''
for i in n:
    answer += str(ord(i) - 64) + ' '
print(answer)

# ----------------------------------------------------------------------

# 1938. 아주 간단한 계산기

a, b = map(int, input().split())
print(a + b)
print(a - b)
print(a * b)
print(a // b)

# ----------------------------------------------------------------------

# 2043. 서랍의 비밀번호
p, k = map(int, input().split())
print(p - k + 1)

# ----------------------------------------------------------------------

# 2019. 더블더블
n = int(input())
for i in range(n+1):
    print(2**i, end=' ')

# ----------------------------------------------------------------------

# 2025. N줄덧셈
n = int(input())
answer = 0
for i in range(1, n+1):
    answer += i
print(answer)

# ----------------------------------------------------------------------

# 2027. 대각선 출력하기
text = list('++++')

for i in range(5):
    text.insert(i, '#')
    print(''.join(text))
    text = list('++++')

# ----------------------------------------------------------------------

# 2029. 몫과 나머지 출력하기
tc = int(input())
for t in range(1, tc+1):
    a, b = map(int, input().split())
    print('#{} {} {}'.format(t, a // b, a % b))

# ----------------------------------------------------------------------

# 1933. 간단한 N 의 약수
n = int(input())
for i in range(1, n+1):
    if (n % i) == 0:
        print(i, end='')