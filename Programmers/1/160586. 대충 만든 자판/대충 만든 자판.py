# 특정 문자열을 작성할 때, 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지
# 단, 작성할 수 없다면 -1 출력
def solution(keymap, targets):
    answer = []
    for target in targets:
        cnt = 0  # 총 횟수
        for s in target:    # ABCD
            alpha = s       # 눌러야하는 알파벳
            pushMin = 210000000   # 번호를 누른 최소 횟수
            
            for key in keymap:
                key = list(key)   # count 함수 활용하기 위해 자료형 변경
                if alpha in key:  # 알파벳이 있는지 체크
                    pushMin = min(pushMin, key.index(alpha)+1)
            
            cnt += pushMin

        if cnt >= 210000000:
            answer.append(-1)
        else:
            answer.append(cnt)
    
    return answer