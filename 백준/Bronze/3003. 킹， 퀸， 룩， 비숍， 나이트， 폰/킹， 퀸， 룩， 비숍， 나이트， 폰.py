chess = list(map(int, input().split()))
peace = [1, 1, 2, 2, 2, 8]

for i, j in zip(chess, peace):
    need = j-i
    print(need, end=' ')