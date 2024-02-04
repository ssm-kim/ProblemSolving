def solution(lottos, win_nums):
    firstRank, lastRank = 7, 7
    for myNum in lottos:
        if myNum == 0:         # 0은 원하는 수로 변경 가능
            firstRank -= 1     # 최고등수만 변경
        if myNum in win_nums:  # 내 번호가 당첨번호목록안에 있으면 증감필수
            firstRank -= 1
            lastRank -= 1
    # 내 번호가 당첨번호목록안에 하나도 없다면 최소등수로 초기화
    if firstRank >= 7:
        firstRank = 6
    if lastRank >= 7:
        lastRank = 6
        
    answer = [firstRank, lastRank]
    
    return answer