n = int(input())
standing = list(map(int, input().split()))
stack = list()
target = 1

while standing:
    curr = standing.pop(0)
    if curr == target:
        target += 1
    else:
        stack.append(curr)

    for i in range(len(stack)-1, -1, -1):
        if stack[i] == target:
            stack.pop()
            target += 1
        else:
            break
        
print('Nice' if not stack else 'Sad')