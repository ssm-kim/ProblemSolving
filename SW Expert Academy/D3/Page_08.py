import sys
sys.stdin = open('./public_input.txt', 'r')

# 9280. 진용이네 주차타워
'''
    문제 이해 및 접근방법
     - 로직을 천천히 생각해보기
'''
# tc = int(input())
# for t in range(1, tc+1):
#     n, m = map(int, input().split())
#     fee = [int(input()) for _ in range(n)]  # n개의 줄에 i번째 주차 공간의 단위 무게당 요금 R이 정수로 주어짐
#     parking = [0] * n
#     carWeight = [int(input()) for _ in range(m)]  # 차량의 i의 무게가 정수로 주어진다. 차량번호 i와 도착순서는 관계 없음
#     sequence = [int(input()) for _ in range(m*2)]
#     waiting = list()  # 대기차량
#     profit = 0  # 총 수익
#     for i in range(len(sequence)):
#         state = sequence[i]

#         if waiting:
#             for j in range(n):
#                 if parking[j] == 0:
#                     parking[j] = waiting.pop(0)
#                     break

#         if state > 0:    # 양수라면 입차
#             for j in range(n):
#                 if parking[j] == 0:
#                     parking[j] = state
#                     break
#             else:  # 주차공간이 없다면 대기차량으로 분리
#                 waiting.append(state)

#         elif state < 0:  # 음수라면 출차
#             for j in range(n):
#                 if parking[j] == abs(state):  # 출차되면서 수익에 추가
#                     parking[j] = 0
#                     profit += (fee[j] * carWeight[abs(state)-1])
#                     break
#     print('#{} {}'.format(t, profit))

# ----------------------------------------------------------------------

# 6019. 기차 사이의 파리
'''
    문제 이해 및 접근방법
     - 두 기차 A와 B가 충돌하는 시간을 구한다,.
     - 파리가 그 시간동안 일정한 속도로 비행을 하기 때문에 시간과 속력을 곱해서 답을 도출한다.
'''
# tc = int(input())
# for t in range(1, tc+1):
#     d, a, b, f = map(int, input().split())
#     time = d/(a+b)  # a와 b 기차가 충돌하는 시간
#     print('#{} {:.6f}'.format(t, time*f))

# ----------------------------------------------------------------------

# 2817. 부분 수열의 합  >  백트래킹 (BFS, DFS 공부이후 풀어보기)
'''
    문제 이해 및 접근방법
'''

def dfs(v, sum):
    global cnt
    if v == n:
        if sum == k:
            cnt += 1
        return
    
    dfs(v+1, sum + a[v])
    dfs(v+1, sum)

tc = int(input())
for t in range(1, tc+1):
    n, k = map(int, input().split())
    a = list(map(int, input().split()))
    cnt = 0
    dfs(0, 0)
    print('#{} {}'.format(t, cnt))


# ----------------------------------------------------------------------

# 20019. 회문의 회문
'''
    문제 이해 및 접근방법
'''
# def palindrome(s):
#     for i in range(len(s)//2):
#         if s[i] != s[len(s)-i-1]:
#             return False
#     return True

# tc = int(input())
# for t in range(1, tc+1):
#     s = list(input())
#     answer = 'NO'
#     if palindrome(s):  # S는 회문인지 확인
#         if palindrome(s[:len(s)//2]) and palindrome(s[len(s)//2+1:]):
#             answer = 'YES'
#     print('#{} {}'.format(t, answer))

# ----------------------------------------------------------------------

# 1873. 상호의 배틀필드
'''
    문제 이해 및 접근방법
'''



# ----------------------------------------------------------------------

# 10580. 전봇대
'''
    문제 이해 및 접근방법
'''



# ----------------------------------------------------------------------