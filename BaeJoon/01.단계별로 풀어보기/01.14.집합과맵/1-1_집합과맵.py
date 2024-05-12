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
n = int(input())
myCards = list(map(int, input().split()))
m = int(input())
cards = list(map(int, input().split()))

# --------------------------------------------------