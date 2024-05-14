tc = int(input())
for _ in range(tc):
    st = list(input())
    stack = list()
    answer = 'YES'
    for i in st:
        if i == '(':
            stack.append(i)
        elif i == ')':
            if stack and (stack[-1] == '('):
                stack.pop()
            else:
                stack.append(i)
    if stack:
        answer = 'NO'
    print(answer)