import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''

# 27866. 문자와 문자열

# s = input()
# n = int(input())
# print(s[n-1])

# --------------------------------------------------
# 11654. 아스키코드

# s = input()
# print(ord(s))

# --------------------------------------------------
# 10809. 알파벳 찾기

# s = input()
# alpha = {chr(i):-1 for i in range(97, 123)}

# for i in range(len(s)):
#     if alpha[s[i]] == -1:
#         alpha[s[i]] = i

# print(' '.join(map(str, alpha.values())))

# ------------------------------------------)--------
# 2675. 문자열 반복

# tc = int(input())
# for t in range(1, tc+1):
#     r, s = input().split()
#     answer = ''
#     for i in s:
#         answer += (i * int(r))
#     print(answer)
    
# --------------------------------------------------
# 1152. 단어의 개수
# s = input().split()
# print(len(s))

# --------------------------------------------------
# 2908. 상수
# a, b = input().split()
# print(max(int(a[-1::-1]), int(b[-1::-1])))

# --------------------------------------------------

# 5622. 다이얼
# '알파벳' : 초단위
# dial_number = {'ABC': 1, 'DEF': 2,  'GHI': 3, 'JKL': 4,
#                'MNO': 5, 'PQRS': 6, 'TUV': 7, 'WXYZ': 8}
# s = list(input())
# answer = 0

# for i in range(len(s)):
#     alpha = s[i]
#     for k, v in dial_number.items():
#         if alpha in k:
#             answer += (v+2)  # 숫자 1을 걸려면 총 2초가 필요하므로 기본값 2로 설정
#             break

# print(answer)

# --------------------------------------------------

# 11718. 그대로 출력하기
while True:
    try:
        n = input()
        print(n)
    except:
        break