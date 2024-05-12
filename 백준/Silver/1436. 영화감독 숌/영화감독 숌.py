n = int(input())
start = 0
checkNum = 0
while True:
    if n == checkNum:
        break
    start += 1
    if "666" in str(start):
        checkNum += 1

print(start)