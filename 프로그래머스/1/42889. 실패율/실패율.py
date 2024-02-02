def solution(N, stages):
    answer = []
    stages.sort()
    dict = {}
    for i in range(N):
        dict[i+1] = 0
        
    for i in stages:
        if i in dict:
            dict[i] += 1
    
    length = len(stages)
    fail = list()       # 실패율 
    for k, v in dict.items():
        if length <= 0:
            fail.append([k, v])    
        else:
            fail.append([k, v/length])
        length -= v

    fail.sort(key=lambda x:x[1], reverse=True)
    for rank, rate in fail:
        answer.append(rank)
        
    return answer