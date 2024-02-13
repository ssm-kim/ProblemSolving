def solution(players, callings):
    answer = []
    rank = { players[i]:i for i in range(len(players)) }
    pos = players  # 원래 선수들 위치 리스트
    # print(rank, pos)
    # pos 리스트로 선수 위치 확인, rank 딕셔너리로 등수 확인
    for call in callings:
        tmp = pos[rank[call]]
        frontIdx = pos[rank[call]]   # 따라잡힌 선수이름
        
        pos[rank[call]] = pos[rank[call]-1]
        backIdx = pos[rank[call]-1]  # 따라잡는 선수이름
        
        pos[rank[call]-1] = tmp
        
        # print(pos, frontIdx, backIdx)
        rank[frontIdx] -= 1
        rank[backIdx] += 1
    # print(rank, pos)
    answer = pos
    
    return answer
