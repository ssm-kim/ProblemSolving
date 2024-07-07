while True:
    a, b, c = map(int, input().split())
    if a==0 and b==0 and c==0:
        break

    answer = ''
    tmp = [a, b, c]
    longest = max(tmp)
    tmp.remove(longest)

    if longest >= sum(tmp):
        answer = 'Invalid'
    elif a==b and b==c:
        answer = 'Equilateral'
    elif a==b or b==c or a==c:
        answer = 'Isosceles'
    elif a!=b and b!=c and a!=c:
        answer = 'Scalene'
    print(answer)