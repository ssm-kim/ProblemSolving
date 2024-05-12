n = int(input())
x = list(map(int, input().split()))  # x, y 좌표이므르 중복제거
copy_x = list(set(x[:]))
copy_x.sort()  # 값이 작은 순으로 정렬한다.
dict = {}

for i in range(len(copy_x)):  # 정렬된 순서에 따라 0 ~ n까지 인덱스를 부여한다. 
                              # 인덱스는 나보다 작은 수의 개수라고 생각하면 된다.
    dict[copy_x[i]] = i

for i in x:
    print(dict[i], end=' ')