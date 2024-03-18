# 여벌이 있는 x번째 학생은 x-1, x+1에게만 체육복을 빌려줄 수 있음
# 적절히 빌려 최대한 많은 학생이 체육수업을 들을 수 있어야 함
def solution(n, lost, reserve):
    answer = 0
    check = [1] * (n+1)   # 총 학생 수
    
    for i in lost:        # 잃어버린 체육복 인원 0으로 초기화
        if i in reserve:  # 여벌 체육복을 가져온 학생이 도난당했을 때
            reserve.remove(i)
            continue
        check[i] = 0
    
    # 값이 순서대로 아닐수도 있으므로 정렬
    lost.sort()     
    reserve.sort()  
    
    for i in range(len(reserve)):
        pos = reserve[i]  # 학생 위치 (몇번째인지)
        lt, rt = pos-1, pos+1
        
        if lt >= 0 and rt <= n:   # 범위 안에 있을 시
            if check[lt] == 0:    # (x-1)번째  >  여벌이 있다면 왼쪽 먼저 체크
                check[lt] = 1
            elif check[rt] == 0:  # (x+1)번째
                check[rt] = 1
        elif rt > n:              # rt가 n보다 크다면
            if check[lt] == 0:
                check[lt] = 1
    
    answer = check.count(1)-1     # 0번째 인덱스 제거 -1
    
    return answer