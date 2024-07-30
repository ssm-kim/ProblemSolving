n = int(input())
myCards = list(map(int, input().split()))
total = {}
for i in myCards:
    if i not in total:
        total[i] = 1
    else:
        total[i] += 1

m = int(input())
cards = list(map(int, input().split()))
for i in cards:
    if i in total:
        print(total[i], end=' ')
    else:
        print(0, end=' ')