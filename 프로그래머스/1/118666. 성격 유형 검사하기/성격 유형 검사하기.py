def check(a, b, c, d):
    if a < b:
        res = d
    else:
        res = c
    return res

def solution(survey, choices):
    answer = ''
    indicators = { 'R' : 0 , 'T' : 0, 'C' : 0, 'F' : 0,
                   'J' : 0 , 'M' : 0, 'A' : 0, 'N' : 0 }  # 지표는 총 8개
    
    for kind, choice in zip(survey, choices):
        front, back = kind
        if choice == 4:
            continue
        elif choice in [1, 2, 3]:
            if choice == 3:
                indicators[front] += 1
            if choice == 2:
                indicators[front] += 2
            if choice == 1:
                indicators[front] += 3
        elif choice in [5, 6, 7]:
            if choice == 7:
                indicators[back] += 3
            if choice == 6:
                indicators[back] += 2
            if choice == 5:
                indicators[back] += 1
    
    idx, tmp = 0, list()
    for k, v in indicators.items():
        idx += 1
        if (idx % 2) == 0:
            tmp += [k, v]
            answer += check(tmp[1], tmp[3], tmp[0], tmp[2])
            tmp = list()
        else:
            tmp += [k, v]
    
    return answer