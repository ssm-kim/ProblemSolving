n = int(input())
last = n*2-1
answer = [[' '] * last for _ in range(n*2-1)]
half = last//2
for i in range(last):
    if i > half:
        lt, rt = i-half, last+half-i-1
    else:
        lt, rt = half-i, half+i
    for j in range(lt, rt+1):
        answer[i][j] = '*'
    
    print('{}'.format(''.join(answer[i]).rstrip()))