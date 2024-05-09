n, k = map(int, input().split())
measure = list()
for i in range(1, n//2+1):  # n의 약수를 구하자
    lt, rt = i, n-i+1
    if n % lt == 0:
        measure.append(lt)
    if n % rt == 0:
        measure.append(rt)
measure.sort()
if k <= len(measure):
    print(measure[k-1])
else:
    print(0)