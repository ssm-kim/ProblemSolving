def solution(array, commands):
    answer = []
    
    for command in commands:
        start, end, point = command
        tmp = sorted(array[start-1:end])
        answer.append(tmp[point-1])
    
    return answer