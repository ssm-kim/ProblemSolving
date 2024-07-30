n = int(input())
commands = [list(map(int, input().split())) for _ in range(n)]
stack, res = list(), list()
for command in commands:
    if len(command) == 2:
        a, b = command
        stack.append(b)
    else:
        x = command[0]
        if x == 2:
            if stack:
                res.append(stack.pop())
            else:
                res.append(-1)
        elif x == 3:
            res.append(len(stack))
        elif x == 4:
            if stack:
                res.append(0)
            else:
                res.append(1)
        else:
            if stack:
                res.append(stack[-1])
            else:
                res.append(-1)
for i in res:
    print(i)