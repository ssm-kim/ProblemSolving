def solution(ingredient):
    answer = 0
    stack = list()
    for i in range(len(ingredient)):
        stack.append(ingredient[i])
        if stack[-4:] == [1, 2, 3, 1]:
            # stack = stack[:len(stack)-4]  >  시간초과
            # 방법 1
            del stack[-4:]
            # 방법 2
            # stack.pop()
            # stack.pop()
            # stack.pop()
            # stack.pop()
            answer += 1
            
    return answer
