a = int(input())
b = int(input())
c = int(input())
answer = ''
if a == 60 and b == 60 and c == 60:
    answer = 'Equilateral'
elif (a+b+c == 180) and (a == b or b == c or a == c):
    answer = 'Isosceles'
elif (a+b+c == 180) and a != b and b != c:
    answer = 'Scalene'
else:
    answer = 'Error'
print(answer)