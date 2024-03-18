def solution(k, m, score):
    answer = 0
    score.sort(reverse=True)
    
    for i in range(len(score)//m):
        minScore, appleCnt = min(score[i*m:(i+1)*m]), len(score[i*m:(i+1)*m])
        answer += (minScore * appleCnt)
    
    return answer