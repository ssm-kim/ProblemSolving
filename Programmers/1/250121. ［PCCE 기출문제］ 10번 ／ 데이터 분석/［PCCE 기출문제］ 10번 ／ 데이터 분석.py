def solution(data, ext, val_ext, sort_by):
    answer = []
    dictSort = {'code' : 0, 'date' : 1, 'maximum' : 2, 'remain' : 3}
    tmp = list()
    # data에서 ext 값이 val_ext보다 작은 데이터만 뽑는다.
    for i in data:
        col1, col2, col3, col4  = i
        if dictSort[ext] == 0:
            if col1  < val_ext:
                tmp.append(i)
        if dictSort[ext] == 1:
            if col2  < val_ext:
                tmp.append(i)
        if dictSort[ext] == 2:
            if col3  < val_ext:
                tmp.append(i)
        if dictSort[ext] == 3:
            if col4  < val_ext:
                tmp.append(i)

    # sort_by에 해당하는 값을 기준으로 오름차순으로 정렬
    tmp.sort(key=lambda x:x[dictSort[sort_by]])
    
    answer = tmp
    
    return answer