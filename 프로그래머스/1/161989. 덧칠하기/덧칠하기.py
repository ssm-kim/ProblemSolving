def solution(n, m, section):
    cnt = 1             # 페인트칠 횟수를 저장할 변수
    start = section[0]  # 페인트칠 기준 위치를 첫 번째 구간으로 초기화
    
    for i in section:
        if start + (m - 1) < i:
            start = i
            cnt += 1
    
    return cnt