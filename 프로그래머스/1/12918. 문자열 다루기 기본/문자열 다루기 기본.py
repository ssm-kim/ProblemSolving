def solution(s):
    answer = True
    if len(s) in (4, 6):
        for i in s:
            if i.isalpha():
                answer = False
                break
    else:
        answer = False
            
    return answer