n = int(input())
record = dict()
for i in range(n):
    name, state = input().split()
    record[name] = state
answer = list()
for k, v in record.items():
    if v == 'enter':
        answer.append(k)
answer.sort(reverse=True)
for i in answer:
    print(i)