n, m = map(int, input().split())
a = {i:0 for i in list(map(int, input().split()))}
b = {i:0 for i in list(map(int, input().split()))}
answer = 0
for i in a:
    if i not in b:
        answer += 1

for i in b:
    if i not in a:
        answer += 1
print(answer)