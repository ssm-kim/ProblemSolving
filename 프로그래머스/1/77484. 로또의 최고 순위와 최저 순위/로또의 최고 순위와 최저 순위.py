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