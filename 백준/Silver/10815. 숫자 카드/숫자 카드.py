n = int(input())
myCards = list(map(int, input().split()))
m = int(input())
cards = list(map(int, input().split()))
dict = {cards[i]:0 for i in range(m)}

for i in myCards:
    if i in dict:
        dict[i] += 1

for i in dict.values():
    print(i, end=' ')