def solution(t, p):
    answer = 0
    for i in range(len(t)-len(p)+1):
        checkNum = t[i:i+len(p)]  # 부분문자열
        if checkNum <= p:         # p보다 작거나 같으면
            answer += 1
            
    return answer