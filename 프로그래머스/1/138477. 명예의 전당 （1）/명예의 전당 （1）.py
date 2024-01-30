def solution(k, score):
    answer, honor = [], []
    fall = -1
    for i in score:
        honor.append(i)
        if len(honor) < k:
            answer.append(min(honor))
        else:
            if i < fall:  # 현재점수가 마지막으로 나간 점수보다 작다면 갱신중지
                honor[k-1] = fall
            honor.sort(reverse=True)  # 명예의 전당 i번째 인원 추가 후 정렬
            fall = honor.pop()
            answer.append(fall)
    
    return answer