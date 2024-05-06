import sys
sys.stdin = open('../input.txt', 'r')

n = int(input())
score = list(map(int, input().split()))

max_score = max(score)
new_score = list()

for x in score:
    new_score.append(x * 100 / max_score)
print(sum(new_score) / len(new_score))
