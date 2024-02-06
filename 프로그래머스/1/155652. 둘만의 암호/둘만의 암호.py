def solution(s, skip, index):
    answer = ''
    alpha = list(map(chr, range(97,123)))  # chr, ord 함수 활용
    for rm in skip: 
        alpha.remove(rm)
    
    for i in s:
        idx = alpha.index(i) + index
        if idx >= len(alpha):    # alpha 리스트 길이보다 같거나 길다면 나머지 연산
            idx = idx % len(alpha)
        answer += alpha[idx]
    
    return answer