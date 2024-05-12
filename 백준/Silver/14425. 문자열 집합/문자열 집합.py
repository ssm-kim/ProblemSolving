n, m = map(int, input().split())
st_n = [input() for _ in range(n)]
st_m = [input() for _ in range(m)]
cnt = 0
for i in st_m:
    if i in st_n:
        cnt += 1
print(cnt)
