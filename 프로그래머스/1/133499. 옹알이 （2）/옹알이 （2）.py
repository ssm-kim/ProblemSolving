def solution(babbling):
    answer = 0
    words = ["aya", "ye", "woo", "ma"]  # 4가지 발음과 4가지 발음으로 만들 수 있는 조합
    
    for i in range(len(babbling)):
        comp = ''  # 비교할 단어
        temp = ''  # 임시저장할 단어
        
        for char in babbling[i]:
            comp += char
            
            if comp == temp:  # 연속해서 같은 단어가 나오면 반복문 탈출
                break
            
            if comp in words:  # 4가지 단어 중 하나가 포함되어 있다면 임시저장 후 비교단어 초기화
                temp = comp
                comp = ''

        if comp == '':  # 비교할 단어가 존재하지 않을 시 +1
            answer += 1
            
    return answer