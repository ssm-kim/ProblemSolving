n, m = map(int, input().split())
listenNot = {input():0 for _ in range(n)}  # 듣도 못한 사람
lookNot = [input() for _ in range(m)]     # 보도 못한 사람
answer = list()

for i in lookNot:
    if i in listenNot.keys():
        answer.append(i)
answer.sort()
print(len(answer))
for i in answer:
    print(i)