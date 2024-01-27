def solution(n):
    answer = 0
    tmp = ''
    while n > 0:
        remain = n % 3
        tmp += str(remain)  # 앞뒤 반전한 값
        n //= 3
    
    for i in range(len(tmp)):
        answer += int(tmp[-i-1]) * (3 ** i)  # ** 제곱 연산자 활용

    return answer