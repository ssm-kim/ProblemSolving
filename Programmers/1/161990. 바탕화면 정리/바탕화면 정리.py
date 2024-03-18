def solution(wallpaper):
    x, y = [], []  # 모든 paper의 x, y 좌표 값
    for i in range(len(wallpaper)):
        for j in range(len(wallpaper[i])):
            if wallpaper[i][j] == '#':
                x.append(i)
                y.append(j)
    x.sort()  # 정렬을 통해 xy좌표의 최솟값, 최댓값 파악
    y.sort()
    lux, luy, rdx, rdy = x[0], y[0], x[-1]+1, y[-1]+1  # 끝점은 +1을 해줘야 모든 블록이 포함됨
    
    answer = [lux, luy, rdx, rdy]
    
    return answer