def solution(s):
    answer = 0
    same, diff = [], []
    for i in s:
        if not same:        # 같은글자 리스트에 아무것도 없다면 추가
            same.append(i)
            continue
            
        if same[-1] == i:   # 이전글자와 현재글자가 같다면 same 추가
            same.append(i)
        else:
            diff.append(i)  # 이전글자와 현재글자가 다르다면 diff 추가
        
        if len(same) == len(diff):  # 같은글자리스트와 다른글자리스트 길이가 같다면 +1해주고 same, diff 초기화
            answer += 1
            # print(same, diff)
            same, diff = [], []
            
    if same or diff:  # 두개의 리스트 중 하나라도 값이 남았다면 +1
        answer += 1
        
    return answer