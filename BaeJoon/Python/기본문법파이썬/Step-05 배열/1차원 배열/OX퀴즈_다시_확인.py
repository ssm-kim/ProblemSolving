import sys
sys.stdin = open('../input.txt', 'r')

# 참고 풀이 --> 조금 더 빠르고 효율적
n = int(sys.stdin.readline())
score = 0
cnt = 1
for _ in range(n):
    for x in sys.stdin.readline().strip():
        if x == 'O':
            score += cnt
            cnt += 1
        else:
            cnt = 1
    print(score)
    score=0
    cnt=1

# 내 풀이
# for _ in range(n):
#     res = 0
#     answer = list(sys.stdin.readline().rstrip())
#     if answer[0] == 'O':
#         score = 1
#     else:
#         score = 0
#     res += score
#     for x in answer[1:]:
#         if x == 'O':
#             score = score + 1
#         else:
#             score = 0
#         res+=score
#     print(res)