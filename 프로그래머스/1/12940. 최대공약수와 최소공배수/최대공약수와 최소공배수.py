def gcd(a, b): # 최대공약수
    for i in range(1, min(a, b)+1):
        if a%i == 0 and b%i == 0:
            num = i
    return num

def lcd(a, b, gcd): # 최소공배수
    num = (a * b) // gcd
    return num

def solution(n, m):
    answer = []
    answer.append(gcd(n, m))
    answer.append(lcd(n, m, answer[-1]))
    
    return answer















# def gcd(a, b):
#     while b > 0:
#         a, b = b, a % b
#     return a
# def lcm(a, b, numMax):
#     return a * b // numMax
# def solution(n, m):
#     answer = []
#     # 유클리드 호제법
#     # 최대 공약수
#     answer.append(gcd(n, m))
#     answer.append(lcm(n, m, gcd(n, m)))
#     print(answer)
#     return answer