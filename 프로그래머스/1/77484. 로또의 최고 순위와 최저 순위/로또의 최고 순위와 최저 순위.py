# 다른 사람 코드 참조 후 다시 풀어본 코드
def solution(lottos, win_nums):
    answer = []
    rank = [6, 6, 5, 4, 3, 2, 1]
    zeroIdx = lottos.count(0)
    curIdx = 0
    for num in lottos:
        if num > 0 and num in win_nums:
            curIdx += 1
            
    answer = [rank[curIdx + zeroIdx], rank[curIdx]]
    return answer

# 최초 나의 풀이
# def solution(lottos, win_nums):
#     firstRank, lastRank = 7, 7
#     for myNum in lottos:
#         if myNum == 0:         # 0은 원하는 수로 변경 가능
#             firstRank -= 1     # 최고등수만 변경
#         if myNum in win_nums:  # 내 번호가 당첨번호목록안에 있으면 증감필수
#             firstRank -= 1
#             lastRank -= 1
#     # 내 번호가 당첨번호목록안에 하나도 없다면 최소등수로 초기화
#     if firstRank >= 7:
#         firstRank = 6
#     if lastRank >= 7:
#         lastRank = 6

#     answer = [firstRank, lastRank]

#     return answer