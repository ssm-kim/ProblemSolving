def boom(doll):  # 인형 터뜨리는 함수
    for i in range(len(doll)-2, -1, -1):  # 현재 인덱스(cur), 다음 인덱스(next) 값이 같다면 +1 후 제거
        next, cur = doll[i+1], doll[i]
        if next == cur:  # 제거 후 반복문 종료
            doll.pop(i)
            doll.pop(i)
            return 2
    return 0

def solution(board, moves):
    answer = 0
    doll = list()       # 뽑은 인형
    for move in moves:  # 뽑을 번호
        target = move-1
        for i in range(len(board)):
            val = board[i][target] 
            if val != 0:             # 0이 아니면 인형번호
                doll.append(val)     # 인형을 뽑고
                board[i][target] = 0      # 해당 자리는 0으로 초기화
                break
        if len(doll) >= 2:  # 뽑은 인형이 0이 아니고 길이가 2이상 boom 체크
            answer += boom(doll)
        
    return answer