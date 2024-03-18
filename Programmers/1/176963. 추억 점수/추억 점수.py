def solution(name, yearning, photo):
    answer = []
    for peoples in photo:
        score = 0
        for people in peoples:
            if people in name:
                score += yearning[name.index(people)]
        answer.append(score)
    
    return answer