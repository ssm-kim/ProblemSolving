def solution(d, budget):
    answer, tmp = 0, 0
    d.sort()
    
    for i in range(len(d)):
        tmp += d[i]
        if tmp <= budget:
            answer = i+1
    
    
    return answer