def solution(board, h, w):
    answer = 0
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    
    for i in range(4):      # 범위안에 있는지 체크 후 북동남서 4방향 탐색
        nx, ny = h+dx[i], w+dy[i]
        
        if not (0 <= nx < len(board) and 0 <= ny < len(board)):
            continue

        if board[h][w] == board[nx][ny]:  # 고정값과 4방향값 비교
            answer += 1
        
    return answer