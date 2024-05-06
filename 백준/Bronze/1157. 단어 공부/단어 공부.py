s = input().upper()
words = dict()
for i in s:
    if i not in words:
        words[i] = 1
    else:
        words[i] += 1

cnt = 0
for k, v in words.items():
    if v == max(words.values()):
        cnt += 1
        answer = k
if cnt == 1:
    print(answer)
else:
    print('?')