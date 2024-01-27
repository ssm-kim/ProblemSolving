def solution(s):
    answer = ''
    s = s.split(' ')     # 전체기준이 아니라 단어별로 인덱스 계산
    for i in range(len(s)):
        if i >= 1:  # 공백이면 추가
            answer += ' '
            
        for j in range(len(s[i])):
            if (j%2)==0:     # 짝수 대문자
                answer += s[i][j].upper()
            else:            # 홀수 소문자
                answer += s[i][j].lower()
    
    return answer