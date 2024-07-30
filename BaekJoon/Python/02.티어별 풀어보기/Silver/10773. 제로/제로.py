k = int(input())
stack = list()
for i in range(k):
    num = int(input())
    if num == 0:
        stack.pop()
    else:
        stack.append(num)
print(sum(stack))
