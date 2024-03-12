def solution(X, Y):
    answer = ''
    dictX, dictY = {i:0 for i in range(0, 10)}, {i:0 for i in range(0, 10)}
    
    for i in range(10):
        dictX[i] = X.count(str(i))
        dictY[i] = Y.count(str(i))
    
    for k in range(9, -1, -1):
        minCnt = min(dictX[k], dictY[k])
        # if answer == '' and k == 0 and minCnt > 0:
        #     answer = '0'
        #     break
            
        answer += (str(k) * minCnt)
    
    if not answer:
        answer = '-1'
    
    if len(answer) == answer.count('0'):
        answer = '0'
        
    return answer