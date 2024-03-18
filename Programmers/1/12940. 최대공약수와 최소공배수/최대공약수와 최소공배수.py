def gcd(a, b): # 최대공약수  >  GCD(A,B) = GCD(B,R)
    for i in range(1, min(a, b)+1):
        if a%i == 0 and b%i == 0:
            num = i
    return num

def lcd(a, b, gcd): # 최소공배수  >  A*B // GCD(최대공약수)
    num = (a * b) // gcd
    return num

def solution(n, m):
    answer = list()
    answer.append(gcd(n, m))
    answer.append(lcd(n, m, answer[-1]))
    return answer
