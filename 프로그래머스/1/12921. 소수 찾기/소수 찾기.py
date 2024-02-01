def solution(n):
    answer = 0
    check = [True, True] + [False] * (n-1)
    prime = list()
    for i in range(2, n+1):  # 1은 소수가 아님
        if not check[i]:     # 소수인데 Fasle라면
            prime.append(i)
            for j in range(i, n+1, i):
                check[j] = True
    
    answer = len(prime)
    
    return answer