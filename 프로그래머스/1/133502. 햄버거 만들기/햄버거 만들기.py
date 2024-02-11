def solution(ingredient):
    answer = 0
    stack = list()
    for i in range(len(ingredient)):
        stack.append(ingredient[i])
        if stack[-4:] == [1, 2, 3, 1]:
            # stack = stack[:len(stack)-4]
            stack.pop()
            stack.pop()
            stack.pop()
            stack.pop()
            answer += 1
            
    return answer