board = [list(input()) for _ in range(5)]
answer = ''
maxLength = 0
for i in board:
    maxLength = max(maxLength, len(i))

for i in range(maxLength):
    for j in range(5):
        try:
            answer += board[j][i]
        except:
            continue
print(answer)