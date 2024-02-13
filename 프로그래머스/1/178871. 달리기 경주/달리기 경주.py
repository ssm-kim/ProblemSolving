def solution(players, callings):
    answer = []
    rank = { players[i]:i for i in range(len(players)) }  

    for call in callings:  # players : 선수 위치 확인, rank : 등수 확인
        idx = rank[call]
        rank[call] -= 1            # 따라잡힌 선수 -1
        rank[players[idx-1]] += 1  # 따라잡은 선수 +1
        players[idx], players[idx-1] = players[idx-1], players[idx]  # 선수 위치 서로 변경
        
    answer = players
    
    return answer