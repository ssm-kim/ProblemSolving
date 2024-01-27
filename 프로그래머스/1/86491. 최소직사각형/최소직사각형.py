def solution(sizes):
    answer = 0
    row, col = [], []
    for width, height in sizes:
        if width > height:
            row.append(width)
            col.append(height)
        else:
            row.append(height)
            col.append(width)
    answer = max(row) * max(col)
    return answer