def solution(s):
    answer = []
    for i in range(len(s)):
        cnt = 0
        for j in range(i-1, -1, -1):
            cnt += 1
            if s[i] == s[j]:
                answer.append(cnt)
                break
        else:
            answer.append(-1)
            
    return answer