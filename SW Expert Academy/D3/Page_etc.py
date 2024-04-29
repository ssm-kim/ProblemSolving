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
tc = int(input())
for t in range(1, tc+1):
    n = int(input())
    bus_route = [list(map(int, input().split())) for _ in range(n)]  # 버스 노선 (A, B)
    p = int(input())
    bus_stop = {i:0 for i in range(1, 5001)}  # 1 ~ 5000번 버스정류장
    
    for start, stop in bus_route:
        for i in range(start, stop+1):
            bus_stop[i] += 1

    p_stop = [int(input()) for _ in range(p)]  # p번 버스정류장

    print('#{} {}'.format(t, ' '.join([str(bus_stop[i]) for i in p_stop])))