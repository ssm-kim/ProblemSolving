n = int(input())
coordinate = [list(map(int, input().split())) for _ in range(n)]

if n == 1:
    answer = 0
else:
    row, col = list(), list()
    for x, y in coordinate:
        row.append(x)
        col.append(y)
    
    answer = (max(row)-min(row)) * (max(col)-min(col))
print(answer)