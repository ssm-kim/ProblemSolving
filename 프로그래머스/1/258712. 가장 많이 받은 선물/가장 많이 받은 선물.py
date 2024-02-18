from collections import defaultdict

def solution(friends, gifts):
    # 선물지수, 주고받은내역
    score, combi = defaultdict(int), defaultdict(int)
    
    for friend in friends:
        score[friend] = 0
    
    for gift in gifts:
        giver, taker = gift.split()
        combi[(giver, taker)] += 1
        score[giver] += 1  # 선물 준 횟수
        score[taker] -= 1  # 선물 받은 회수
    
    answer = list()
    for i in friends:  # 다음달에 선물받는 대상 기준
        tmp = 0
        for j in friends:
            if i==j:
                continue
            
            if combi[(i, j)] > combi[(j, i)]:  # 더 많이 선물을 준 사람이 다음달에 선물 1개를 받는 로직
                tmp += 1
            if combi[(i, j)] == combi[(j, i)] and score[i] > score[j]:  # 선물지수에 따른 로직
                tmp += 1
        answer.append((i, tmp))  # 받을 사람, 선물 받을 횟수
    
    answer.sort(key=lambda x:x[1], reverse=True)
    answer = answer[0][-1]
    
    return answer