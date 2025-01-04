import sys
sys.stdin = open('../input.txt', 'r')

'''
    10문제씩 나눠서 분류
'''
# 10815. 숫자 카드
# n = int(input())
# myCards = list(map(int, input().split()))
# m = int(input())
# cards = list(map(int, input().split()))
# dict = {cards[i]:0 for i in range(m)}

# for i in myCards:
#     if i in dict:
#         dict[i] += 1

# for i in dict.values():
#     print(i, end=' ')

# --------------------------------------------------

# 14425. 문자열집합
# n, m = map(int, input().split())
# st_n = [input() for _ in range(n)]
# st_m = [input() for _ in range(m)]
# cnt = 0
# for i in st_m:
#     if i in st_n:
#         cnt += 1
# print(cnt)

# --------------------------------------------------

# 7785. 회사에 있는 사람
# n = int(input())
# record = dict()
# for i in range(n):
#     name, state = input().split()
#     record[name] = state
# answer = list()
# for k, v in record.items():
#     if v == 'enter':
#         answer.append(k)
# answer.sort(reverse=True)
# for i in answer:
#     print(i)

# --------------------------------------------------

# 1620.나는야 포켓몬 마스터 이다솜  >  Pass

# --------------------------------------------------

# 10816.숫자 카드 2
'''
    문제풀이방법 및 접근
        - 상근의 카드를 딕셔너리에 초기화한다.
'''
# n = int(input())
# myCards = list(map(int, input().split()))
# total = {}
# for i in myCards:
#     if i not in total:
#         total[i] = 1
#     else:
#         total[i] += 1

# m = int(input())
# cards = list(map(int, input().split()))
# for i in cards:
#     if i in total:
#         print(total[i], end=' ')
#     else:
#         print(0, end=' ')

# --------------------------------------------------

# 1764. 듣보잡
'''
    문제풀이방법 및 접근
'''
# n, m = map(int, input().split())
# listenNot = {input():0 for _ in range(n)}  # 듣도 못한 사람
# lookNot = [input() for _ in range(m)]     # 보도 못한 사람
# answer = list()

# for i in lookNot:
#     if i in listenNot.keys():
#         answer.append(i)
# answer.sort()
# print(len(answer))
# for i in answer:
#     print(i)

# --------------------------------------------------

# 1269. 대칭차집합
'''
    문제풀이방법 및 접근
        - 두 집합 공통으로 없는 원소 개수를 찾으면 된다.
'''
# n, m = map(int, input().split())
# a = {i:0 for i in list(map(int, input().split()))}
# b = {i:0 for i in list(map(int, input().split()))}
# answer = 0
# for i in a:
#     if i not in b:
#         answer += 1

# for i in b:
#     if i not in a:
#         answer += 1
# print(answer)

# --------------------------------------------------

# 11478. 서로 다른 부분 문자열의 개수
'''
    문제풀이방법 및 접근
    0 1 2 3 4
    01 12 23 34
    012 123 234
    0123 1234
    01234
'''

s = list(input())
answer = len(set(s))  # 1개짜리 부분문자열 (중복제거)
check_dict = dict()
lt, rt = -1, 1
for i in range(1, len(s)):
    for j in range(i, len(s)):
        st = ''.join(s[lt+j:rt+j])   # 2개 이상의 부분 문자열
        if st not in check_dict:
            check_dict[st] = 1
            answer += 1
    lt -= 1
print(answer)