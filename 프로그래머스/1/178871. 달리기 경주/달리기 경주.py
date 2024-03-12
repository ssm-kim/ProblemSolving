# 풀이방법 보고 다시 풀이한 코드  >  이해하기 쉬움

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

'''
힌트없이 풀이한 코드
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
            
            pos[rank[call]-1] = tmp      # 선수변경
            
            # print(pos, frontIdx, backIdx)
            rank[frontIdx] -= 1          # 등수변경
            rank[backIdx] += 1           # 등수변경
        # print(rank, pos)
        answer = pos
        
        return answer
'''
