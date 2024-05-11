n, m = map(int, input().split())
card = list(map(int, input().split()))
res = 0
for i in range(n-2):
    for j in range(i+1, n-1):
        for k in range(j+1, n):
            total = card[i] + card[j] + card[k]
            if m >= total:
                # 최대값 찾기
                if total >= res:
                    res = total
print(res)